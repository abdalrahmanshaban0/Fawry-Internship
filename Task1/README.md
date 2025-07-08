# 📚 Bookstore System

A simple Java-based bookstore system supporting multiple book types, stock management, sales, and outdated item cleanup.

## Features
- Add or remove
- Supports different book types:
  - `PaperBook` (stock + optional shipping)
  - `EBook` (sent via email)
  - `DemoBook` (not for sale)
- Buy and ship paper books
- Buy and send EBooks via email
- Detect and remove outdated books based on publication year and the given passed years
- Singleton `ShippingService` and `MailService` simulate external actions

## Screenshot
![Task1](https://github.com/user-attachments/assets/c4b54f5e-4480-45ec-b7ff-c588e555350e)
