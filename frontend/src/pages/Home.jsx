import React from "react";
import Navbar from "../components/Navbar";
import Slider from "../components/Slider";
import ProductSection from "../components/ProductSection";
import Footer from "../components/Footer";

const Home = () => {
  return (
    <div className="bg-[#1e1e2f] text-white min-h-screen">
      <Navbar />
      <Slider />
      <ProductSection />
      <Footer />
    </div>
  );
};

export default Home;
