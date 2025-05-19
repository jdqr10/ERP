import React from "react";
import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <nav className="bg-gradient-to-r from-[#1e1e2f] to-[#2c2c44] text-white px-6 py-4 shadow-md flex justify-between items-center">
      <h1 className="text-xl font-bold">ERP Lite</h1>

      <div className="flex items-center space-x-6">
        <ul className="flex space-x-6 text-sm">
          <li className="hover:text-cyan-400 cursor-pointer">Inicio</li>
          <li className="hover:text-cyan-400 cursor-pointer">Módulos</li>
        </ul>

        <Link to="/login">
          <button className="bg-cyan-600 hover:bg-cyan-700 text-white px-4 py-2 rounded-lg text-sm transition duration-200">
            Iniciar Sesión
          </button>
        </Link>
      </div>
    </nav>
  );
};

export default Navbar;
