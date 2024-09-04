import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
import './css/index.css'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import React from 'react';
import Layout from './pages/layout';
import Index from './pages/index/index';
import OverMij from './pages/overMij';
import Lessen from './pages/lessen/lessen';
import Les from './pages/les/les';

function App() {
  return (
    <BrowserRouter>
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element={<Index />} />
        <Route path="over-mij" element={<OverMij />} />
        <Route path="lessen" element={<Lessen />} />
        <Route path="les/:id" element={<Les />} />
      </Route>
    </Routes>
  </BrowserRouter>
  );
}

export default App;
