class Car:
    def __init__(self, car_id, brand, model, base_price_per_day):
        self.car_id = car_id
        self.brand = brand
        self.model = model
        self.base_price_per_day = base_price_per_day
        self.is_available = True

    def get_car_id(self):
        return self.car_id

    def get_brand(self):
        return self.brand

    def get_model(self):
        return self.model

    def calculate_price(self, rental_days):
        return self.base_price_per_day * rental_days

    def is_available(self):
        return self.is_available

    def rent(self):
        self.is_available = False

    def return_car(self):
        self.is_available = True


class Customer:
    def __init__(self, customer_id, name):
        self.customer_id = customer_id
        self.name = name

    def get_customer_id(self):
        return self.customer_id

    def get_name(self):
        return self.name


class Rental:
    def __init__(self, car, customer, days):
        self.car = car
        self.customer = customer
        self.days = days

    def get_car(self):
        return self.car

    def get_customer(self):
        return self.customer

    def get_days(self):
        return self.days


class CarRentalSystem:
    def __init__(self):
        self.cars = []
        self.customers = []
        self.rentals = []

    def add_car(self, car):
        self.cars.append(car)

    def add_customer(self, customer):
        self.customers.append(customer)

    def rent_car(self, car, customer, days):
        if car.is_available:
            car.rent()
            self.rentals.append(Rental(car, customer, days))
        else:
            print("Машин түрээслэх боломжгүй.")
    def return_car(self, car):
        car.return_car()
        rental_to_remove = None
        for rental in self.rentals:
            if rental.get_car() == car:
                rental_to_remove = rental
                break
        if rental_to_remove:
            self.rentals.remove(rental_to_remove)
        else:
            print("Машин түрээслээгүй байна.")
    def menu(self):
        while True:
            print("===== Машин түрээсийн систем =====")
            print("1. Машин түрээслэх")
            print("2. Машин буцаах")
            print("3. Гарах")
            choice = input("Та сонголтоо оруулна уу: ")

            if choice == '1':
                print("\n== Машин түрээслэх ==\n")
                customer_name = input("Та нэрээ бичнэ үү!: ")

                print("\nБоломжит машинууд :")
                for car in self.cars:
                    if car.is_available:
                        print(f"{car.get_car_id()} - {car.get_brand()} {car.get_model()}")

                car_id = input("\nТүрээслэхийг хүссэн машиныхаа ID-г бичнэ уу!: ")
                rental_days = int(input("Хэдэн өдөр түрээслэхээ бичнэ үү!: "))

                new_customer = Customer(f"Үйлчлүүлэгч{len(self.customers) + 1}", customer_name)
                self.add_customer(new_customer)

                selected_car = None
                for car in self.cars:
                    if car.get_car_id() == car_id and car.is_available:
                        selected_car = car
                        break

                if selected_car is not None:
                    total_price = selected_car.calculate_price(rental_days)
                    print("\n== Түрээсийн мэдээ ==\n")
                    print("Үйлчлүүлэгч ID:", new_customer.get_customer_id())
                    print("Үйлчлүүлэгч нэр:", new_customer.get_name())
                    print("Машин:", selected_car.get_brand(), selected_car.get_model())
                    print("Түрээслэх хоног:", rental_days)
                    print(f"Нийт үнэ:{total_price:.2f} төгрөг")

                    confirm = input("\nТүрээсийг баталгаажуулах (Y/N): ")
                    if confirm.upper() == "Y":
                        self.rent_car(selected_car, new_customer, rental_days)
                        print("\nМашин амжилттай түрээсллээ.")
                    else:
                        print("\nТүрээс цуцлагдлаа .")
                else:
                    print("\nБуруу машин сонголт эсвэл машин түрээслэх боломжгүй.")

            elif choice == '2':
                print("\n== Машин буцаах ==\n")
                car_id = input("Буцаах машиыхаа ID-г бичнэ үү! : ")

                car_to_return = None
                for car in self.cars:
                    if car.get_car_id() == car_id and not car.is_available:
                        car_to_return = car
                        break

                if car_to_return is not None:
                    customer = None
                    for rental in self.rentals:
                        if rental.get_car() == car_to_return:
                            customer = rental.get_customer()
                            break

                    if customer is not None:
                        self.return_car(car_to_return)
                        print("Машин", customer.get_name(),"-аар амжилттай буцаагдлаа")
                    else:
                        print("Машин түрээслээгүй эсвэл түрээсийн мэдээлэл дутуу байна.")
                else:
                    print("Буруу машины ID эсвэл машин түрээслээгүй.")

            elif choice == '3':
                break

            else:
                print("Буруу сонголт. Зөв сонголт оруулна уу.")

        print("\nМашин түрээсийн системийг ашигласанд баярлалаа!")


# Creating instances of CarRentalSystem and cars
rental_system = CarRentalSystem()

car1 = Car("C001", "Toyota", "Camry", 60.0)
car2 = Car("C002", "Honda", "Accord", 70.0)
car3 = Car("C003", "Mahindra", "Thar", 150.0)

rental_system.add_car(car1)
rental_system.add_car(car2)
rental_system.add_car(car3)

rental_system.menu()
