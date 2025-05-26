import { useState } from "react";
import axios from "axios";

export default function InscriptionModal({ show, onClose, eventId, onSuccess }) {
  const [nom, setNom] = useState("");
  const [email, setEmail] = useState("");
  const [error, setError] = useState(null);

  if (!show) return null;

  const handleSubmit = (e) => {
    e.preventDefault();
    setError(null);

    axios.post(`http://localhost:8080/api/evenements/${eventId}/inscrire`, {
      nom, email
    })
    .then(() => {
      onSuccess("✅ Inscription réussie !");
      onClose();
    })
    .catch(err => {
      const msg = err.response?.data?.message || "❌ Erreur d’inscription";
      setError(msg);
    });
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center z-50">
      <div className="bg-white rounded-lg p-6 w-full max-w-md shadow-lg">
        <h2 className="text-lg font-semibold mb-3">📝 Inscription</h2>

        <form onSubmit={handleSubmit} className="space-y-3">
          <input
            type="text"
            placeholder="Nom"
            value={nom}
            onChange={(e) => setNom(e.target.value)}
            className="w-full border rounded px-3 py-2 text-sm"
            required
          />
          <input
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className="w-full border rounded px-3 py-2 text-sm"
            required
          />

          {error && <p className="text-sm text-red-600">{error}</p>}

          <div className="flex justify-end gap-2 mt-3">
            <button
              type="button"
              onClick={onClose}
              className="text-sm px-3 py-1 border rounded hover:bg-gray-100"
            >
              Annuler
            </button>
            <button
              type="submit"
              className="bg-blue-600 text-white text-sm px-4 py-1 rounded hover:bg-blue-700"
            >
              Valider
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
