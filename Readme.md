Bu projede, araba verileri düzenli bir şekilde yönetilmekte ve farklı özellikleri temsil eden entity sınıfları aracılığıyla ilişkilendirilmektedir. 
Ana entity sınıfı olan CarEntity, arabaların genel bilgilerini içermektedir. Diğer önemli entity sınıfları arasında MarkaEntity, ModelEntity, MotorEntity, OzellikEntity, PaketEntity, ve KasaEntity bulunmaktadır.
Bu sınıflar, sırasıyla araç markası, modeli, motoru, özellikleri, paketi ve kasası gibi detayları temsil etmektedir.

Proje, Spring framework'ü kullanılarak geliştirilmiş olup, CarService sınıfı aracılığıyla arabaların temel işlemleri gerçekleştirilmektedir.
Bu işlemler arasında belirli bir aracın detaylarına ulaşma, yeni bir araç ekleme, varolan bir aracı güncelleme ve bir aracı silme gibi temel
veritabanı operasyonları bulunmaktadır.

CarController sınıfı, HTTP isteklerini işleyerek bu arabaların listelenmesi, detaylarına ulaşılması, ekleme, güncelleme ve silme gibi işlemleri sağlamaktadır.
Bu sayede, kullanıcılar tarafından yapılan istekler aracılığıyla arabaların veritabanı işlemleri gerçekleştirilebilmektedir.

Projede dikkat çeken bir nokta, her aracın marka, model, motor, özellik, paket ve kasa gibi ayrıntılarının ID'leri kullanılarak ilişkilendirilmiş olmasıdır.
Bu yapı, veritabanında verilerin düzenli ve bağlantılı bir şekilde tutulmasına olanak sağlar. Bu nedenle, projede her bir aracın detay bilgilerine kolayca erişim 
sağlanabilir ve bu detaylar arasında ilişki kurulabilir. 


Bu projede Caner PAMUK ekip arkadaşımla birlikte çalışılmıştır.
