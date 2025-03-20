# Todo Uygulaması

Basit ve kullanışlı bir konsol tabanlı Todo (Yapılacaklar) uygulaması. Java ile geliştirilmiştir.

## Özellikler

- Yeni görev ekleme
- Görevleri listeleme
- Görev durumunu değiştirme (tamamlandı/tamamlanmadı)
- Görev silme
- Görevleri kalıcı olarak JSON dosyasında saklama

## Gereksinimler

- Java Runtime Environment (JRE)
- Gson kütüphanesi (lib klasöründe mevcuttur)

## Kurulum

1. Projeyi bilgisayarınıza indirin
2. Terminal veya komut istemcisinde proje klasörüne gidin
3. `run_todo.bat` dosyasını çalıştırın

## Kullanım

Program çalıştığında aşağıdaki menü seçenekleri sunulur:

1. Yeni görev ekle
2. Görevleri listele
3. Görev durumunu değiştir
4. Görev sil
5. Çıkış

Görevler otomatik olarak `todos.json` dosyasında saklanır ve program her başlatıldığında bu dosyadan yüklenir. 