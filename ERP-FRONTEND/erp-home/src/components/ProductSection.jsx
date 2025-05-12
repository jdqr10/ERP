import React from 'react';

const ProductSection = () => {
  return (
    <section className="products">
      <h3>Módulos Principales</h3>
      <div className="product-list">
        <div className="product-card">🧑‍💼 Usuarios y Roles</div>
        <div className="product-card">📦 Productos e Inventario</div>
        <div className="product-card">🧾 Órdenes y Facturación</div>
        <div className="product-card">💰 Pagos y Finanzas</div>
        <div className="product-card">📊 Dashboard y Reportes</div>
      </div>
    </section>
  );
};

export default ProductSection;