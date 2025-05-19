import React from "react";

const Footer = () => {
  return (
    <footer className="bg-[#1e1e2f] text-[#bbb] text-center text-sm py-8 px-4">
      <div className="max-w-screen-xl mx-auto">
        <p className="mb-2">
          <a href="/contacto" className="text-cyan-400 hover:underline mx-2">
            Contacto
          </a>{" "}
          |
          <a href="/legal" className="text-cyan-400 hover:underline mx-2">
            Legal
          </a>
        </p>
        <p>
          &copy; {new Date().getFullYear()} ERP Lite. Todos los derechos
          reservados.
        </p>
      </div>
    </footer>
  );
};

export default Footer;
