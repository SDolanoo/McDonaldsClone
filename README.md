# 🍟 McDonald's Offline Loyalty App (Kotlin + Jetpack Compose)

Edukacyjna aplikacja mobilna będąca offline'owym klonem aplikacji lojalnościowej McDonald's.  
Zbudowana w **czystym Kotlinie** z wykorzystaniem **Jetpack Compose**, **Room**, **Hilt** i podejścia **Clean Architecture** oraz **feature-based modularization**.  
Projekt nie wykorzystuje backendu – dane są przechowywane lokalnie.

---

## ✨ Funkcje (MVP)

- 🏠 Strona główna z banerami i skrótami
- 🎟️ MojeM (QR kod + produkty za punkty + lista kuponów do odebrania)
- 🍔 Zamów i odbierz (zamawianie pojedyńczych produktów oraz tworzenie zestawów)
- 🛒 Koszyk (lista produktów - gdy jakieś produkty są w koszyku, podsumowanie zamówienia)
- ⭐ Punkty lojalnościowe (symulacja działania)

---

## 🧰 Stack technologiczny

- **Kotlin + Jetpack Compose**
- **Hilt** (Dependency Injection)
- **Navigation Compose**
- **Modularna architektura (feature-based)**
- **Czysta architektura (Clean Architecture)**

---

## 🗂️ Struktura projektu
app/src/main/java/com/example/mcdonaldsclone

app/
core/
├── composables/
├── daggerHilt/
├── database/
├── navigation/
└── ui/
features/
├── cart/
├── coupons
├── home/
├── loyalty/
├── makeOrder/
├── mojeM/
└── QRCode/

## 🧪 Testy

app/src/test/java/com/example/mcdonaldsclone
