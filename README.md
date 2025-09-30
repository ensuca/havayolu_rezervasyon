# ✈️ Airline Seat Reservation System - Concurrent Programming Demonstration

> A Java-based multi-threaded simulation demonstrating thread synchronization, race conditions, and concurrency control in airline seat booking scenarios

[![Java](https://img.shields.io/badge/Java-17-orange?style=flat&logo=java)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-Build-blue?style=flat&logo=apache-maven)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![Concurrency](https://img.shields.io/badge/Pattern-Reader--Writer-purple)](https://en.wikipedia.org/wiki/Readers%E2%80%93writer_lock)

---

## 🎯 Overview

This project is a **comprehensive demonstration of concurrent programming concepts** in Java, implementing a real-world airline seat reservation system where multiple threads compete to book and query seats simultaneously. It showcases both **problematic race conditions** and **proper synchronization solutions** using Java's concurrency utilities.

The system simulates a scenario where multiple customers (threads) attempt to:
- Query available seats
- Book specific seats
- Cancel reservations
- Handle concurrent access to shared resources

**Why This Project Matters:**
- Demonstrates deep understanding of Java's concurrency framework
- Illustrates the classic **Reader-Writer problem** with practical solutions
- Shows the critical difference between thread-safe and non-thread-safe implementations
- Provides educational value for understanding race conditions and their prevention

---

## ✨ Key Features

### 🔒 **Thread Synchronization Mastery**
- Implementation of both **locked** and **unlocked** versions for educational comparison
- Demonstrates race condition scenarios and their solutions
- Uses Java's `ReentrantLock` for fine-grained concurrency control

### 🧵 **Multi-Threading Architecture**
- Spawns 24 concurrent threads (12 readers + 12 writers) competing for resources
- Simulates real-world high-contention scenarios in booking systems
- Random seat selection and operation types for realistic simulation

### 📊 **Reader-Writer Pattern Implementation**
- `Reader` class: Queries seat availability without locking (demonstrates issues)
- `RWL` (Reader With Lock): Thread-safe seat query implementation
- `Writer` class: Performs bookings/cancellations without locking
- `WWL` (Writer With Lock): Thread-safe booking/cancellation operations

### 💺 **Airline Seat Management**
- 12-seat configuration (4 rows × 3 columns: A, B, C)
- Real-time seat status tracking (available/booked)
- Customer ID assignment for seat tracking
- Booking and cancellation operations

### 🎲 **Realistic Simulation**
- Random seat selection from available inventory
- Probabilistic booking vs. cancellation decisions
- Concurrent access patterns mimicking real booking systems

---

## 🛠️ Tech Stack

| Category | Technology | Purpose |
|----------|-----------|---------|
| **Language** | Java 17 | Core programming language with modern features |
| **Build Tool** | Maven | Dependency management and build automation |
| **Concurrency** | java.util.concurrent | Threading, locks, and synchronization primitives |
| **Data Structures** | HashMap | Efficient seat inventory management |
| **IDE** | IntelliJ IDEA | Development environment (configured) |

**Why Java 17?**
- Modern LTS version with enhanced performance
- Improved concurrency utilities
- Better memory management for multi-threaded applications

---

## 🏗️ Architecture & Design

### Design Pattern: Reader-Writer Problem
This project implements a classic concurrency pattern where:
- **Readers** (query operations) can execute concurrently
- **Writers** (booking/cancellation) require exclusive access
- Demonstrates both incorrect (unlocked) and correct (locked) implementations

### Class Structure

```
┌─────────────────────────────────────────┐
│              Main.java                  │
│  (Thread orchestration & simulation)    │
└───────────────┬─────────────────────────┘
                │
                ├─► Ucak.java (Airplane)
                │   └─► Seat inventory management
                │
                ├─► Reader.java (Unlocked reader)
                │   └─► Demonstrates race condition
                │
                ├─► RWL.java (Reader With Lock)
                │   └─► Thread-safe queries
                │
                ├─► Writer.java (Unlocked writer)
                │   └─► Demonstrates race condition
                │
                └─► WWL.java (Writer With Lock)
                    └─► Thread-safe bookings
```

### Key Components

**`Ucak` (Airplane Class)**
- Manages seat inventory using HashMap
- Provides atomic operations: `biletAlım()`, `biletIptali()`, `koltukBilgi()`
- Central shared resource accessed by all threads

**Lock Implementations**
- **Without Locks** (`Reader`, `Writer`): Demonstrates potential race conditions
- **With Locks** (`RWL`, `WWL`): Shows proper synchronization using `ReentrantLock`

### Concurrency Strategy
- **Fine-grained locking** for minimal contention
- **Try-finally blocks** ensuring locks are always released
- **Thread identification** for tracking booking ownership

---

## 🚀 Getting Started

### Prerequisites

- **Java Development Kit (JDK) 17** or higher
- **Maven 3.6+** for build management
- **Git** for cloning the repository

```bash
# Verify installations
java -version    # Should show Java 17+
mvn -version     # Should show Maven 3.6+
```

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/ensuca/havayolu_rezervasyon.git
cd havayolu_rezervasyon
```

2. **Build the project**
```bash
mvn clean compile
```

3. **Run the simulation**
```bash
mvn exec:java -Dexec.mainClass="Main"
```

**Alternative: Using compiled classes**
```bash
# Compile
javac -d target/classes src/main/java/*.java

# Run
java -cp target/classes Main
```

---

## 💻 Usage Examples

### Basic Execution
Run the main program to see concurrent booking simulation:

```bash
java Main
```

**Expected Output:**
```
Writer 0 booked seat 2B successfully.
Reader 1 looks for available seats.
{1A=bos, 1B=345, 1C=bos, 2A=bos, 2B=346, 2C=bos, ...}
Writer 2 booked seat 1C successfully.
...
```

### Understanding the Output
- **Thread names** indicate operation type and thread number
- **Seat status**: `bos` (empty) or customer ID (booked)
- **Operations**: Booking and query operations interleaved
- **Race conditions**: Without locks, you may see inconsistent states

### Comparing Lock vs. No-Lock Behavior

**Modify `Main.java` to test different scenarios:**

```java
// Scenario 1: Without locks (demonstrates race conditions)
threads.add(new Thread(new Writer(ucak, koltuk), "Writer " + i));
threads.add(new Thread(new Reader(ucak), "Reader " + i));

// Scenario 2: With locks (proper synchronization)
threads.add(new Thread(new WWL(ucak, koltuk, lock), "Writer " + i));
threads.add(new Thread(new RWL(ucak, lock), "Reader " + i));
```

---

## 📁 Project Structure

```
havayolu_rezervasyon/
├── src/
│   └── main/
│       └── java/
│           ├── Main.java          # Application entry point & thread orchestration
│           ├── Ucak.java          # Airplane class (seat inventory management)
│           ├── Reader.java        # Unlocked read operations (race condition demo)
│           ├── Writer.java        # Unlocked write operations (race condition demo)
│           ├── RWL.java           # Read-With-Lock (thread-safe queries)
│           └── WWL.java           # Write-With-Lock (thread-safe bookings)
├── target/
│   └── classes/                   # Compiled .class files
├── pom.xml                        # Maven configuration
├── .gitattributes                 # Git configuration
└── README.md                      # Project documentation
```

### File Descriptions

| File | Purpose | Lines of Code |
|------|---------|---------------|
| **Main.java** | Thread creation, orchestration, random seat generation | ~38 |
| **Ucak.java** | Seat inventory, booking/cancellation logic | ~45 |
| **Reader.java** | Query operations without synchronization | ~20 |
| **Writer.java** | Booking operations without synchronization | ~33 |
| **RWL.java** | Synchronized read operations with ReentrantLock | ~32 |
| **WWL.java** | Synchronized write operations with ReentrantLock | ~47 |

---

## 🔍 Deep Dive: Concurrency Concepts Demonstrated

### 1. Race Conditions
The `Reader` and `Writer` classes intentionally omit locks to demonstrate race conditions:
- Multiple threads accessing shared `HashMap` simultaneously
- Potential for data corruption and inconsistent reads
- Lost updates when multiple writers modify the same seat

### 2. Critical Sections
The `Ucak.biletAlım()` method represents a critical section:
```java
// Check-then-act pattern (NOT atomic without locks)
if (Koltuk_Plani.get(koltukNo).equals("bos")) {
    Koltuk_Plani.put(koltukNo, Integer.toString(musteriNo)); // Race condition here!
    return true;
}
```

### 3. Lock-Based Solutions
`WWL` and `RWL` classes demonstrate proper synchronization:
```java
lock.lock();
try {
    // Critical section protected
    rezervasyon(); // Thread-safe operation
} finally {
    lock.unlock(); // Guaranteed release
}
```

### 4. Thread Safety Guarantees
With locks:
- **Mutual Exclusion**: Only one writer at a time
- **Visibility**: Changes made by one thread visible to others
- **Atomicity**: Operations complete without interruption

---

## 📊 Performance & Scalability

### Current Configuration
- **Threads**: 24 (12 readers + 12 writers)
- **Seats**: 12 (3×4 configuration)
- **Contention**: High - multiple threads competing for same resources

### Observed Behavior
- **Without locks**: Fast but potentially incorrect results
- **With locks**: Slightly slower but guaranteed correctness
- **Scalability**: Demonstrates how contention affects performance

### Optimization Opportunities
1. **Read-Write Locks**: Use `ReadWriteLock` to allow concurrent reads
2. **Lock-Free Structures**: Consider `ConcurrentHashMap` for seat inventory
3. **Fine-Grained Locking**: Lock individual seats instead of entire map
4. **Thread Pool**: Reuse threads instead of creating new ones

---

## 🧪 Learning Outcomes

This project demonstrates proficiency in:

✅ **Concurrent Programming**: Multi-threaded application design
✅ **Synchronization Primitives**: ReentrantLock, proper lock handling
✅ **Race Condition Analysis**: Identifying and preventing concurrency bugs
✅ **Design Patterns**: Reader-Writer problem implementation
✅ **Java Best Practices**: Try-finally blocks, thread naming, resource management
✅ **Problem Solving**: Simulating real-world high-contention scenarios

---

## 🔮 Roadmap & Future Enhancements

### Planned Improvements
- [ ] Implement `ReadWriteLock` for optimized read concurrency
- [ ] Add unit tests using JUnit 5 and multi-threaded testing frameworks
- [ ] Create GUI visualization of concurrent booking operations
- [ ] Add metrics collection (throughput, contention, wait times)
- [ ] Implement priority queue for customer fairness
- [ ] Add support for seat types (economy, business, first class)
- [ ] Database persistence layer for seat inventory
- [ ] RESTful API for remote booking operations

### Known Limitations
- Current `Reader`/`Writer` classes lack synchronization (intentional for demonstration)
- HashMap is not thread-safe; consider `ConcurrentHashMap`
- No transaction rollback mechanism for failed bookings
- Limited seat configuration (hardcoded 12 seats)

---

## 🤝 Contributing

Contributions are welcome! This project serves as an educational resource, and improvements are encouraged.

### How to Contribute
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Areas for Contribution
- Enhanced visualization of thread interactions
- Additional concurrency patterns (Producer-Consumer, Semaphores)
- Performance benchmarking tools
- Documentation improvements
- Test coverage expansion

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👤 Contact & Connect

**Developer**: Enes
**Project Link**: [https://github.com/ensuca/havayolu_rezervasyon](https://github.com/ensuca/havayolu_rezervasyon)

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-blue?style=flat&logo=linkedin)](https://linkedin.com/in/enes-uca-41039327b)
[![GitHub](https://img.shields.io/badge/GitHub-Follow-black?style=flat&logo=github)](https://github.com/ensuca)
[![Portfolio](https://img.shields.io/badge/Portfolio-Visit-green?style=flat&logo=google-chrome)](https://ensuca.github.io/ensuca.githubio)

---

## 🙏 Acknowledgments

- Inspired by classic concurrency problems in operating systems courses
- Java concurrency utilities documentation
- Research papers on the Reader-Writer problem

---

<div align="center">

**⭐ If you find this project helpful for learning concurrency concepts, please consider giving it a star! ⭐**

</div>

---
---

# 🇹🇷 Türkçe

# ✈️ Havayolu Koltuk Rezervasyon Sistemi - Eşzamanlı Programlama Demonstrasyonu

> Havayolu koltuk rezervasyonunda thread senkronizasyonu, yarış koşulları ve eşzamanlılık kontrolünü gösteren Java tabanlı çok thread'li simülasyon

[![Java](https://img.shields.io/badge/Java-17-orange?style=flat&logo=java)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-Build-blue?style=flat&logo=apache-maven)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![Concurrency](https://img.shields.io/badge/Pattern-Reader--Writer-purple)](https://en.wikipedia.org/wiki/Readers%E2%80%93writer_lock)

---

## 🎯 Genel Bakış

Bu proje, **Java'da eşzamanlı programlama kavramlarının kapsamlı bir gösterimidir** ve birden fazla thread'in aynı anda koltuk rezervasyonu yapmak ve sorgulamak için yarıştığı gerçek dünya havayolu koltuk rezervasyon sistemini uygulamaktadır. Hem **sorunlu yarış koşullarını** hem de Java'nın eşzamanlılık araçlarını kullanarak **doğru senkronizasyon çözümlerini** sergiler.

Sistem, birden fazla müşterinin (thread) aşağıdaki işlemleri gerçekleştirmeye çalıştığı bir senaryoyu simüle eder:
- Müsait koltukları sorgulama
- Belirli koltukları rezerve etme
- Rezervasyonları iptal etme
- Paylaşılan kaynaklara eşzamanlı erişimi yönetme

**Bu Projenin Önemi:**
- Java'nın eşzamanlılık framework'üne derin hakimiyeti gösterir
- Pratik çözümlerle klasik **Reader-Writer problemini** açıklar
- Thread-safe ve thread-safe olmayan implementasyonlar arasındaki kritik farkı gösterir
- Yarış koşullarını ve önlenmesini anlamak için eğitsel değer sağlar

---

## ✨ Temel Özellikler

### 🔒 **Thread Senkronizasyonu Ustalığı**
- Eğitsel karşılaştırma için hem **kilitli** hem de **kilitsiz** versiyonların implementasyonu
- Yarış koşulu senaryolarını ve çözümlerini gösterir
- Hassas eşzamanlılık kontrolü için Java'nın `ReentrantLock` kullanımı

### 🧵 **Çok Thread'li Mimari**
- Kaynaklar için yarışan 24 eşzamanlı thread (12 okuyucu + 12 yazıcı) oluşturur
- Rezervasyon sistemlerinde gerçek dünya yüksek rekabet senaryolarını simüle eder
- Gerçekçi simülasyon için rastgele koltuk seçimi ve işlem türleri

### 📊 **Reader-Writer Pattern Implementasyonu**
- `Reader` sınıfı: Kilitleme olmadan koltuk müsaitliğini sorgular (sorunları gösterir)
- `RWL` (Reader With Lock): Thread-safe koltuk sorgulama implementasyonu
- `Writer` sınıfı: Kilitleme olmadan rezervasyon/iptal işlemleri gerçekleştirir
- `WWL` (Writer With Lock): Thread-safe rezervasyon/iptal işlemleri

### 💺 **Havayolu Koltuk Yönetimi**
- 12 koltuk konfigürasyonu (4 sıra × 3 sütun: A, B, C)
- Gerçek zamanlı koltuk durumu takibi (müsait/rezerveli)
- Koltuk takibi için müşteri ID ataması
- Rezervasyon ve iptal işlemleri

### 🎲 **Gerçekçi Simülasyon**
- Mevcut envanterden rastgele koltuk seçimi
- Olasılıksal rezervasyon vs iptal kararları
- Gerçek rezervasyon sistemlerini taklit eden eşzamanlı erişim kalıpları

---

## 🛠️ Teknoloji Yığını

| Kategori | Teknoloji | Amaç |
|----------|-----------|------|
| **Dil** | Java 17 | Modern özelliklerle temel programlama dili |
| **Build Aracı** | Maven | Bağımlılık yönetimi ve build otomasyonu |
| **Eşzamanlılık** | java.util.concurrent | Threading, kilit ve senkronizasyon primitive'leri |
| **Veri Yapıları** | HashMap | Verimli koltuk envanteri yönetimi |
| **IDE** | IntelliJ IDEA | Geliştirme ortamı (yapılandırılmış) |

**Neden Java 17?**
- Gelişmiş performansa sahip modern LTS sürümü
- İyileştirilmiş eşzamanlılık araçları
- Çok thread'li uygulamalar için daha iyi bellek yönetimi

---

## 🏗️ Mimari ve Tasarım

### Tasarım Deseni: Reader-Writer Problemi
Bu proje, aşağıdaki klasik eşzamanlılık desenini uygular:
- **Okuyucular** (sorgulama işlemleri) eşzamanlı olarak çalışabilir
- **Yazıcılar** (rezervasyon/iptal) özel erişim gerektirir
- Hem yanlış (kilitsiz) hem de doğru (kilitli) implementasyonları gösterir

### Sınıf Yapısı

```
┌─────────────────────────────────────────┐
│              Main.java                  │
│  (Thread orkestrasyon ve simülasyon)    │
└───────────────┬─────────────────────────┘
                │
                ├─► Ucak.java (Uçak)
                │   └─► Koltuk envanteri yönetimi
                │
                ├─► Reader.java (Kilitsiz okuyucu)
                │   └─► Yarış koşulunu gösterir
                │
                ├─► RWL.java (Reader With Lock)
                │   └─► Thread-safe sorgular
                │
                ├─► Writer.java (Kilitsiz yazıcı)
                │   └─► Yarış koşulunu gösterir
                │
                └─► WWL.java (Writer With Lock)
                    └─► Thread-safe rezervasyonlar
```

### Ana Bileşenler

**`Ucak` (Uçak Sınıfı)**
- HashMap kullanarak koltuk envanterini yönetir
- Atomik işlemler sağlar: `biletAlım()`, `biletIptali()`, `koltukBilgi()`
- Tüm thread'ler tarafından erişilen merkezi paylaşılan kaynak

**Kilit Implementasyonları**
- **Kilitsiz** (`Reader`, `Writer`): Olası yarış koşullarını gösterir
- **Kilitli** (`RWL`, `WWL`): `ReentrantLock` kullanarak doğru senkronizasyonu gösterir

### Eşzamanlılık Stratejisi
- Minimum rekabet için **hassas kilitleme**
- Kilitlerin her zaman serbest bırakılmasını sağlayan **try-finally blokları**
- Rezervasyon sahipliğini izlemek için **thread tanımlama**

---

## 🚀 Başlangıç

### Gereksinimler

- **Java Development Kit (JDK) 17** veya üzeri
- Build yönetimi için **Maven 3.6+**
- Repository'yi klonlamak için **Git**

```bash
# Kurulumları doğrula
java -version    # Java 17+ göstermeli
mvn -version     # Maven 3.6+ göstermeli
```

### Kurulum

1. **Repository'yi klonlayın**
```bash
git clone https://github.com/ensuca/havayolu_rezervasyon.git
cd havayolu_rezervasyon
```

2. **Projeyi derleyin**
```bash
mvn clean compile
```

3. **Simülasyonu çalıştırın**
```bash
mvn exec:java -Dexec.mainClass="Main"
```

**Alternatif: Derlenmiş sınıfları kullanma**
```bash
# Derle
javac -d target/classes src/main/java/*.java

# Çalıştır
java -cp target/classes Main
```

---

## 💻 Kullanım Örnekleri

### Temel Çalıştırma
Eşzamanlı rezervasyon simülasyonunu görmek için ana programı çalıştırın:

```bash
java Main
```

**Beklenen Çıktı:**
```
Writer 0 booked seat 2B successfully.
Reader 1 looks for available seats.
{1A=bos, 1B=345, 1C=bos, 2A=bos, 2B=346, 2C=bos, ...}
Writer 2 booked seat 1C successfully.
...
```

### Çıktıyı Anlama
- **Thread isimleri** işlem türünü ve thread numarasını gösterir
- **Koltuk durumu**: `bos` (boş) veya müşteri ID (rezerveli)
- **İşlemler**: Rezervasyon ve sorgulama işlemleri iç içe
- **Yarış koşulları**: Kilit olmadan tutarsız durumlar görebilirsiniz

### Kilitli vs Kilitsiz Davranışı Karşılaştırma

**Farklı senaryoları test etmek için `Main.java`'yı değiştirin:**

```java
// Senaryo 1: Kilitsiz (yarış koşullarını gösterir)
threads.add(new Thread(new Writer(ucak, koltuk), "Writer " + i));
threads.add(new Thread(new Reader(ucak), "Reader " + i));

// Senaryo 2: Kilitli (doğru senkronizasyon)
threads.add(new Thread(new WWL(ucak, koltuk, lock), "Writer " + i));
threads.add(new Thread(new RWL(ucak, lock), "Reader " + i));
```

---

## 📁 Proje Yapısı

```
havayolu_rezervasyon/
├── src/
│   └── main/
│       └── java/
│           ├── Main.java          # Uygulama giriş noktası ve thread orkestrasyon
│           ├── Ucak.java          # Uçak sınıfı (koltuk envanteri yönetimi)
│           ├── Reader.java        # Kilitsiz okuma işlemleri (yarış koşulu demo)
│           ├── Writer.java        # Kilitsiz yazma işlemleri (yarış koşulu demo)
│           ├── RWL.java           # Read-With-Lock (thread-safe sorgular)
│           └── WWL.java           # Write-With-Lock (thread-safe rezervasyonlar)
├── target/
│   └── classes/                   # Derlenmiş .class dosyaları
├── pom.xml                        # Maven konfigürasyonu
├── .gitattributes                 # Git konfigürasyonu
└── README.md                      # Proje dokümantasyonu
```

### Dosya Açıklamaları

| Dosya | Amaç | Kod Satırı |
|-------|------|------------|
| **Main.java** | Thread oluşturma, orkestrasyon, rastgele koltuk üretimi | ~38 |
| **Ucak.java** | Koltuk envanteri, rezervasyon/iptal mantığı | ~45 |
| **Reader.java** | Senkronizasyon olmadan sorgulama işlemleri | ~20 |
| **Writer.java** | Senkronizasyon olmadan rezervasyon işlemleri | ~33 |
| **RWL.java** | ReentrantLock ile senkronize okuma işlemleri | ~32 |
| **WWL.java** | ReentrantLock ile senkronize yazma işlemleri | ~47 |

---

## 🔍 Derinlemesine İnceleme: Gösterilen Eşzamanlılık Kavramları

### 1. Yarış Koşulları
`Reader` ve `Writer` sınıfları yarış koşullarını göstermek için kasıtlı olarak kilit kullanmaz:
- Birden fazla thread'in paylaşılan `HashMap`'e aynı anda erişmesi
- Veri bozulması ve tutarsız okumalar potansiyeli
- Birden fazla yazıcı aynı koltuğu değiştirdiğinde kayıp güncellemeler

### 2. Kritik Bölümler
`Ucak.biletAlım()` metodu kritik bir bölümü temsil eder:
```java
// Kontrol-sonra-hareket deseni (kilit olmadan atomik DEĞİL)
if (Koltuk_Plani.get(koltukNo).equals("bos")) {
    Koltuk_Plani.put(koltukNo, Integer.toString(musteriNo)); // Burada yarış koşulu!
    return true;
}
```

### 3. Kilit Tabanlı Çözümler
`WWL` ve `RWL` sınıfları doğru senkronizasyonu gösterir:
```java
lock.lock();
try {
    // Korunan kritik bölüm
    rezervasyon(); // Thread-safe işlem
} finally {
    lock.unlock(); // Garanti edilmiş serbest bırakma
}
```

### 4. Thread Güvenliği Garantileri
Kilitlerle:
- **Karşılıklı Dışlama**: Aynı anda sadece bir yazıcı
- **Görünürlük**: Bir thread'in yaptığı değişiklikler diğerlerine görünür
- **Atomiklik**: İşlemler kesintisiz tamamlanır

---

## 📊 Performans ve Ölçeklenebilirlik

### Mevcut Konfigürasyon
- **Thread'ler**: 24 (12 okuyucu + 12 yazıcı)
- **Koltuklar**: 12 (3×4 konfigürasyon)
- **Rekabet**: Yüksek - aynı kaynaklar için yarışan birden fazla thread

### Gözlemlenen Davranış
- **Kilitsiz**: Hızlı ama potansiyel olarak yanlış sonuçlar
- **Kilitli**: Biraz daha yavaş ama garanti edilmiş doğruluk
- **Ölçeklenebilirlik**: Rekabetin performansı nasıl etkilediğini gösterir

### Optimizasyon Fırsatları
1. **Read-Write Locks**: Eşzamanlı okumalara izin vermek için `ReadWriteLock` kullanımı
2. **Lock-Free Yapılar**: Koltuk envanteri için `ConcurrentHashMap` düşünülmesi
3. **Hassas Kilitleme**: Tüm map yerine bireysel koltukların kilitlenmesi
4. **Thread Pool**: Yeni thread oluşturmak yerine thread'lerin yeniden kullanımı

---

## 🧪 Öğrenme Çıktıları

Bu proje aşağıdaki konularda yeterliliği gösterir:

✅ **Eşzamanlı Programlama**: Çok thread'li uygulama tasarımı
✅ **Senkronizasyon Primitive'leri**: ReentrantLock, doğru kilit yönetimi
✅ **Yarış Koşulu Analizi**: Eşzamanlılık hatalarını tanımlama ve önleme
✅ **Tasarım Desenleri**: Reader-Writer problemi implementasyonu
✅ **Java En İyi Uygulamaları**: Try-finally blokları, thread adlandırma, kaynak yönetimi
✅ **Problem Çözme**: Gerçek dünya yüksek rekabet senaryolarını simüle etme

---

## 🔮 Yol Haritası ve Gelecek Geliştirmeler

### Planlanan İyileştirmeler
- [ ] Optimize edilmiş okuma eşzamanlılığı için `ReadWriteLock` implementasyonu
- [ ] JUnit 5 ve çok thread'li test framework'leri kullanarak birim testleri ekleme
- [ ] Eşzamanlı rezervasyon işlemlerinin GUI görselleştirmesi
- [ ] Metrik toplama (throughput, rekabet, bekleme süreleri)
- [ ] Müşteri adaleti için öncelik kuyruğu implementasyonu
- [ ] Koltuk türleri desteği (ekonomi, business, first class)
- [ ] Koltuk envanteri için veritabanı persistence katmanı
- [ ] Uzaktan rezervasyon işlemleri için RESTful API

### Bilinen Sınırlamalar
- Mevcut `Reader`/`Writer` sınıfları senkronizasyondan yoksun (gösteri için kasıtlı)
- HashMap thread-safe değil; `ConcurrentHashMap` düşünülebilir
- Başarısız rezervasyonlar için transaction rollback mekanizması yok
- Sınırlı koltuk konfigürasyonu (hardcoded 12 koltuk)

---

## 🤝 Katkıda Bulunma

Katkılar memnuniyetle karşılanır! Bu proje eğitsel bir kaynak olarak hizmet eder ve iyileştirmeler teşvik edilir.

### Nasıl Katkıda Bulunulur
1. Repository'yi fork edin
2. Özellik branch'i oluşturun (`git checkout -b feature/harika-ozellik`)
3. Değişikliklerinizi commit edin (`git commit -m 'Harika özellik ekle'`)
4. Branch'e push edin (`git push origin feature/harika-ozellik`)
5. Pull Request açın

### Katkı Alanları
- Thread etkileşimlerinin gelişmiş görselleştirmesi
- Ek eşzamanlılık desenleri (Producer-Consumer, Semaphores)
- Performans benchmark araçları
- Dokümantasyon iyileştirmeleri
- Test coverage genişletmesi

---

## 📄 Lisans

Bu proje MIT Lisansı altında lisanslanmıştır - detaylar için [LICENSE](LICENSE) dosyasına bakın.

---

## 👤 İletişim ve Bağlantı

**Geliştirici**: Enes
**Proje Linki**: [https://github.com/ensuca/havayolu_rezervasyon](https://github.com/ensuca/havayolu_rezervasyon)

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Bağlan-blue?style=flat&logo=linkedin)](https://linkedin.com/in/enes-uca-41039327b)
[![GitHub](https://img.shields.io/badge/GitHub-Takip%20Et-black?style=flat&logo=github)](https://github.com/ensuca)
[![Portfolio](https://img.shields.io/badge/Portfolio-Ziyaret%20Et-green?style=flat&logo=google-chrome)](https://ensuca.github.io/ensuca.githubio)

---

## 🙏 Teşekkürler

- İşletim sistemleri derslerindeki klasik eşzamanlılık problemlerinden ilham alınmıştır
- Java eşzamanlılık araçları dokümantasyonu
- Reader-Writer problemi üzerine araştırma makaleleri

---

<div align="center">

**⭐ Eşzamanlılık kavramlarını öğrenmek için bu projeyi faydalı bulduysanız, lütfen yıldız vermeyi düşünün! ⭐**

</div>
