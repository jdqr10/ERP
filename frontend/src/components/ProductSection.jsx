import React from "react";

const ProductSection = () => {
  return (
    <section className="bg-[#929292] text-black py-12 px-4">
      <h3 className="text-2xl font-bold text-center mb-8">
        Módulos Principales
      </h3>
      <div className="flex flex-wrap justify-center gap-6">
        {[
          "🧑‍💼 Usuarios y Roles",
          "📦 Productos e Inventario",
          "🧾 Órdenes y Facturación",
          "💰 Pagos y Finanzas",
          "📊 Dashboard y Reportes",
        ].map((item, index) => (
          <div
            key={index}
            className="bg-white text-black px-6 py-4 rounded-xl shadow-md hover:shadow-xl transition duration-300 text-center w-56"
          >
            {item}
          </div>
        ))}
      </div>
    </section>
  );
};

export default ProductSection;
