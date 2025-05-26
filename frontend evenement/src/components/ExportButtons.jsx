import axios from "axios";

export function ExportButtons() {
  const handleExport = (type) => {
    const url = `http://localhost:8080/api/fichier/export/${type}`;
    axios.get(url)
      .then(() => alert(`✅ Exportation ${type.toUpperCase()} réussie !`))
      .catch(() => alert(`❌ Échec de l'exportation ${type.toUpperCase()}`));
  };

  return (
    <div className="space-x-4 mt-4">
      <button
        onClick={() => handleExport("json")}
        className="bg-green-600 text-white px-3 py-2 rounded hover:bg-green-700"
      >
        📤 Exporter JSON
      </button>
      <button
        onClick={() => handleExport("xml")}
        className="bg-purple-600 text-white px-3 py-2 rounded hover:bg-purple-700"
      >
        📤 Exporter XML
      </button>
    </div>
  );
}
