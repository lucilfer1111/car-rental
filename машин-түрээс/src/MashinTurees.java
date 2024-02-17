package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MashinTurees {
    private List<Mashin> mashinuud;
    private List<Uilchluulegch> uilchluulegchid;
    private List<Turees> tureesuud;

    public MashinTurees() {
        mashinuud = new ArrayList<>();
        uilchluulegchid = new ArrayList<>();
        tureesuud = new ArrayList<>();
    }

    public void mashinNemeh(Mashin mashin) {
        mashinuud.add(mashin);
    }

    public void UilchluulegchNemeh(Uilchluulegch uilchluulegch) {
        uilchluulegchid.add(uilchluulegch);
    }

    public void MashinTureesleh(Mashin mashin, Uilchluulegch uilchluulegch, int honog) {
        if (mashin.bolomjtoiEseh()) {
            mashin.turees();
            tureesuud.add(new Turees(mashin, uilchluulegch, honog));
        } else {
            System.out.println("Энэ машинийг түрээслэх боломжгүй байна.");
        }
    }

    public void returnMashin(Mashin mashin) {
        mashin.returnMashin();
        Turees tureesArilgah = null;
        for (Turees turees : tureesuud) {
            if (turees.getMashin() == mashin) {
                tureesArilgah = turees;
                break;
            }
        }
        if (tureesArilgah != null) {
            tureesuud.remove(tureesArilgah);
        } else {
            System.out.println("Машин түрээслэгдээгүй байна.");
        }
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("-----Машин түрээсийн систем-----");
            System.out.println("1. Машин түрээслэх");
            System.out.println("2. Машин Буцаах");
            System.out.println("3.Гарах");
            System.out.println("Сонголтоо оруулах");
            int songolt = sc.nextInt();
            sc.nextLine();
            if (songolt == 1) {
                System.out.println("\n----Машин түрээс----\n");
                System.out.println("Овог нэрээ бичнэ үү!");
                String ner = sc.nextLine();
                System.out.println("Түрээслэх боломжтой машинууд:");
                for (Mashin mashin : mashinuud) {
                    if (mashin.bolomjtoiEseh()) {
                        System.out.println(mashin.getMashinID() + "-" + mashin.getBrand() + "-" + mashin.getZagvar());
                    }
                }
                System.out.print("\nТүрээслэх машииныхаа id-г бичнэ үү!");
                String mashinID = sc.nextLine();

                System.out.print("\nХэдэн хоног түрээслэх вэ?:");
                int honog = sc.nextInt();
                sc.nextLine();

                Uilchluulegch shineUilchluulegch = new Uilchluulegch("Үйлчлүүлэгч" + (uilchluulegchid.size() + 1), ner);
                UilchluulegchNemeh(shineUilchluulegch);

                Mashin songogdsonMashin = null;
                for (Mashin mashin : mashinuud) {
                    if (mashin.getMashinID().equals(mashinID) && mashin.bolomjtoiEseh()) {
                        songogdsonMashin = mashin;
                        break;
                    }
                }
                if (songogdsonMashin != null) {
                    double niitUne = songogdsonMashin.tureesUneTootsoh(honog);
                    System.out.println("\n---Түрээсийн мэдээлэл---\n");
                    System.out.println("Үйлчлүүлэгч id:" + shineUilchluulegch.getUilchluulegchID());
                    System.out.println("Үйлчлүүлэгч нэр:" + shineUilchluulegch.getNer());
                    System.out.println("Машин:" + songogdsonMashin.getBrand() + "" + songogdsonMashin.getZagvar());
                    System.out.println("Түрээсийн хоног:" + honog);
                    System.out.println("Нийт үнэ: $%.2f%n" + niitUne);

                    System.out.println("\nТүрээслэх үү (Y/N):");
                    String zovshooroh = sc.nextLine();

                    if (zovshooroh.equalsIgnoreCase("Y")) {
                        MashinTureesleh(songogdsonMashin, shineUilchluulegch, honog);
                        System.out.println("\nМашин амжилттай түрээслэгдлээ!");
                    } else {
                        System.out.println("\nМашин түрээс цуцлагдлаа!");
                    }
                } else {
                    System.out.println("\nМашин түрээсэнд байхгүй байна.");
                }
            } else if (songolt == 2) {
                System.out.println("\n---Машин бцуаах---\n");
                System.out.println("Буцаах машиныхаа id-г бичнэ үү!");
                String mashinID = sc.nextLine();

                Mashin mashinButsaah = null;
                for (Mashin mashin : mashinuud) {
                    if (mashin.getMashinID().equals(mashinID) && !mashin.bolomjtoiEseh()) {
                        mashinButsaah = mashin;
                        break;
                    }
                }
                if (mashinButsaah != null) {
                    Uilchluulegch uilchluulegch = null;
                    for (Turees turees : tureesuud) {
                        if (turees.getMashin() == mashinButsaah) {
                            uilchluulegch = turees.getUilchluulegch();
                            break;
                        }
                    }
                    if (uilchluulegch != null) {
                        returnMashin(mashinButsaah);
                        System.out.println("Машин амжилттай буцаагдлаа!" + uilchluulegch.getNer());
                    } else {
                        System.out.println("Машин түрээслэгдээгүй байна эсвэл түрээсийн мэдээлэл дутуу байна!");
                    }
                } else {
                    System.out.println("Машины id буруу байна эсвэл машин түрээслэгдээгүй байна.");
                }
            } else if (songolt == 3) {
                break;
            } else {
                System.out.println("\nМанай машин түрээсийн үйлчилгээг сонгосонд баярлалаа!");
            }
        }
    }
}
