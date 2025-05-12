import React from "react";
import "./App.css";
import Navbar from "./components/Navbar";
import Slider from "./components/Slider";
import ProductSection from "./components/ProductSection";
import Footer from "./components/Footer";

function App() {
  return (
    <div>
      <Navbar />
      <Slider />
      <ProductSection />
      <Footer />
    </div>
  );
}

export default App;
