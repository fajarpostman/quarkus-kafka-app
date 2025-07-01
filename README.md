

# Quarkus Kafka APP

Layanan ini menerima data dari **Kafka**, melakukan manipulasi data (contoh: mengubah nama menjadi huruf kapital), lalu:

- Mengirimkan kembali ke kafka topic lain.

Dibangun dengan arsitek **Clean Architecture** dengan teknologi:

- Java 17
- Quarkus
- Apache Kafka
- Maven

## Cara menjalankan

### 1. Clone Repository

```bash
git clone https://github.com/your-username/quarkus-kafka-app.git

cd quarkus-kafka-app
```

### 2. Instal Dependency

```bash
mvn install
```

### 3. Jalankan Kafka & Zookeeper (Menggunakan Docker)

```bash
docker compose up -d
```

Menjalankan Kafka di port 9092 dan Zookeeper di 2181.

### 4. Jalankan Quarkus App

```bash
mvn compile quarkus:dev
```

### 5. Kirim data ke Kafka

```bash
docker exec -it quarkus-kafka-app-kafka-1 kafka-console-producer --bootstrap-server localhost:9092 --topic user-data-in
```

```bash
Dataset
fajar,29
emil,30
```
Tekan Ctrl+C untuk keluar.

### 6. Cek output kafka

```bash
docker exec -it quarkus-kafka-app-kafka-1 kafka-console-consumer --bootstrap-server localhost:9092 --topic user-data-out --from-beginning
```

Hasil:
```bash
FAJAR,29
EMIL,30
```

### Dibuat oleh

Dibuat oleh Fajar Dwi Rianto

- **Email**: fajardwirianto3@gmail.com

- **Github**: github.com/fajar.postman
