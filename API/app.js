// app.js  —  Mini‑Library API (Express + ES modules)

import express from 'express';
import cors from 'cors';

const app = express();
app.use(cors());
app.use(express.json());

// Bellek‑içi kitap listesi
let books = [
  { id: 1, Kitapadi: 'Yaşama Uğraşı', Yazar: 'Cesare Pavese', Yıl: 1952}
];

// ---------- GET / (ana sayfa) ------------------------------------
app.get('/', (req, res) => {
  res.send('📚 Mini-Library API çalışıyorn Kitaplar için /books yolunu kullanın.');
});

// ---------- GET /books ------------------------------------------
app.get('/books', (req, res) => {
  res.status(200).json(books);
});

// ---------- GET /books/:id --------------------------------------
app.get('/books/:id', (req, res) => {
  const id = Number(req.params.id);
  const book = books.find(b => b.id === id);
  if (!book) return res.sendStatus(404);
  res.status(200).json(book);
});

// ---------- POST /books -----------------------------------------
app.post('/books', (req, res) => {
  const { Kitapadi, Yazar, Yıl } = req.body;

  if (!Kitapadi || !Yazar || !Yıl) {
    return res.status(400).json({ error: 'Kitapadi, Yazar, Yıl zorunlu' });
  }

  const id = books.length ? Math.max(...books.map(b => b.id)) + 1 : 1;
  const book = { id, Kitapadi, Yazar, Yıl };
  books.push(book);
  res.status(201).json(book);
});

// ---------- Sunucuyu başlat -------------------------------------
const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
  console.log(`Mini-Library API is running on http://localhost:${PORT}`);
});
