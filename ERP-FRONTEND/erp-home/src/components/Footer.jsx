import React from 'react';

const Footer = () => {
  return (
    <footer className="footer">
      <p>
        <a href="/contacto">Contacto</a> | <a href="/legal">Legal</a>
      </p>
      <p>&copy; {new Date().getFullYear()} ERP Lite. Todos los derechos reservados.</p>
    </footer>
  );
};

export default Footer;