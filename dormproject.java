

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author seden
 */
public class Dorm {

    public static void main(String[] args) {
        
        String[] odalar = new String[10];
        double[] odaFiyatlari = new double[10];
        boolean[] musaitlikDurumlari = new boolean[10];
        double total;
        String onay;
        
        
        Scanner scan = new Scanner(System.in);
        
        // Oda bilgilerini dosyadan oku
        try (BufferedReader reader = new BufferedReader(new FileReader("oda_bilgileri.txt"))) {
            for (int i = 0; i < 10; i++) {
                String line = reader.readLine();
                if (line != null) {
                    String[] odaBilgileri = line.split(",");
                    odalar[i] = odaBilgileri[0];
                    odaFiyatlari[i] = Double.parseDouble(odaBilgileri[1]);
                    musaitlikDurumlari[i] = Boolean.parseBoolean(odaBilgileri[2]);
                }
            }
        } 
        catch (IOException e) {
            System.err.println("Dosya okuma hatası: " + e);
            return;
        }
         //Oda bilgilerini ekrana yazdır
        System.out.printf("%-5s %-15s %-10s%n %-10s%n", "No", "Oda", "Fiyat","Müsaitlik");
        System.out.println("-----------------------------------");
        for (int k = 0; k < odalar.length; k++) 
        {
            System.out.printf("%-5d %-15s %-10.2f %-10s%n", (k + 1), odalar[k], odaFiyatlari[k],(musaitlikDurumlari[k] ? "Müsait" : "Müsait değil"));
        }
        System.out.println("Fiyatlar dönemliktir.");
        
        //Kullanıcıya oda seçtir
        System.out.println("İstediğiniz odayı giriniz:");
        int odaNo=scan.nextInt();
        odaNo--;
        
        if (musaitlikDurumlari[odaNo])
        {
            // Kullanıcı bilgilerini al
            System.out.print("İsim: ");
            String isim = scan.next();
            System.out.print("Soyisim: ");
            String soyisim = scan.next();
            System.out.print("Telefon Numarası: ");
            String telefonNumarasi = scan.next();
            System.out.print("Öğrenci Numarası: ");
            String ogrenciNumarasi = scan.next();
            System.out.print("Dönem sayısı: ");
            int donem = scan.nextInt();
            
            total=donem*odaFiyatlari[odaNo]*1.16;
            System.out.print("Vergiler Dahil: "+total);
            
            try (PrintWriter writer = new PrintWriter(new FileWriter("ogrenci_bilgileri.txt", true)))

                {
                    writer.print(isim+"\t"+soyisim+"\t"+telefonNumarasi+"\t"+ogrenciNumarasi+"\t"+odalar[odaNo]+"\t");
                    System.out.println("İşleminiz tamamlanmıştır.");
                }
                catch (IOException e)
                {
                        System.err.println("Dosya yazma hatası: " + e);
                }
        }
        else
        {
            System.out.print("Seçtiğiniz odada müsaitlik bulunmamaktadır. Başka oda seçiniz.");
            System.exit(0);
        }
        
    }
}
