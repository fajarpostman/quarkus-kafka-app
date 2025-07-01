\# Quarkus Kafka App

Layanan ini menerima data dari \*\*Kafka\*\*, melakukan manipulasi data (contoh: mengubah nama menjadi huruf kapital), lalu:

\- ✅ Menyimpannya ke \*\*MySQL\*\*, dan

\- ✅ Mengirimkannya kembali ke Kafka topic lain.

Dibangun dengan arsitektur \*\*Clean Architecture\*\* dan teknologi:

\- Java 17

\- Quarkus

\- Apache Kafka

\- MySQL

\- Maven

\---

\## 📁 Struktur Proyek

src/

├── domain/ --> Entity model (UserData)

├── usecase/ --> Business logic

├── repository/ --> JPA repository

├── entrypoint/

│ ├── kafka/ --> Kafka consumer & producer

│ └── rest/ --> (Opsional REST API)

yaml

Copy

Edit

\---

\## 🚀 Cara Menjalankan

\### 1. Clone Repository

\`\`\`bash

git clone https://github.com/your-username/quarkus-kafka-app.git

cd quarkus-kafka-app

Pastikan folder tidak di dalam OneDrive (bisa ganggu build Maven).

2\. Install Dependency (jika belum)


mvn install

3\. Jalankan Kafka & Zookeeper (pakai Docker)


docker compose up -d

Ini akan menjalankan Kafka di port 9092 dan Zookeeper di 2181.

4\. Jalankan Quarkus App


mvn compile quarkus:dev

Aplikasi akan live di http://localhost:8080

5\. Buat Database (jika belum)

Pastikan MySQL aktif (bisa dari XAMPP), lalu buat database:


CREATE DATABASE apache-kafka;

6\. Kirim Data ke Kafka


docker exec -it quarkus-kafka-app-kafka-1 kafka-console-producer --bootstrap-server localhost:9092 --topic user-data-in

Kemudian input data seperti:


fajar,29

emil,30

Tekan Ctrl+C untuk keluar.

7\. Cek Output Kafka Topic


docker exec -it quarkus-kafka-app-kafka-1 kafka-console-consumer --bootstrap-server localhost:9092 --topic user-data-out --from-beginning

Harusnya muncul:


FAJAR,29

EMIL,30

8\. Verifikasi ke MySQL

Buka phpMyAdmin atau terminal:


SELECT * FROM user_data;

🔧 Konfigurasi Penting

📄 src/main/resources/application.properties:

properties


quarkus.datasource.db-kind=mysql

quarkus.datasource.username=root

quarkus.datasource.password=

quarkus.datasource.jdbc.url=jdbc:mysql://localhost:3306/apache-kafka

quarkus.hibernate-orm.database.generation=update

\# Kafka input (consume)

mp.messaging.incoming.user-data-in.connector=smallrye-kafka

mp.messaging.incoming.user-data-in.topic=user-data-in

mp.messaging.incoming.user-data-in.bootstrap.servers=localhost:9092

\# Kafka output (produce)

mp.messaging.outgoing.user-data-out.connector=smallrye-kafka

mp.messaging.outgoing.user-data-out.topic=user-data-out

mp.messaging.outgoing.user-data-out.bootstrap.servers=localhost:9092

📦 Contoh Dataset (CSV Format)


fajar,29

emil,30

john doe,28

alice,31

✍️ Output yang Diharapkan


1FAJAR29

2EMIL30

3JOHN DOE28

4ALICE31

Kafka user-data-out akan menerima data dengan format NAMA_KE_HURUF_BESAR,UMUR.

👨‍💻 Developer Notes

✅ Clean Architecture digunakan untuk separasi domain, usecase, dan infra.

🔄 Kafka consumer dan producer bekerja asinkron.

🧪 Tambahkan unit test untuk usecase bila ingin coverage lebih tinggi.

🛠 TroubleShooting

Port 3306 is busy? → Tutup MySQL Docker jika pakai XAMPP.

Kafka tidak konsumsi? → Periksa bootstrap.servers, topik dan log quarkus:dev.

Path terlalu panjang atau error Maven? → Pindahkan folder dari OneDrive.

📬 Kontak

Dibuat oleh Fajar Dwi Rianto

Email: \[fajardwirianto3@gmail.com\]

Github: \[github.com/fajar.postman\]