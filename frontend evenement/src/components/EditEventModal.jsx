import { useState, useEffect } from "react";
import axios from "axios";

export default function EditEventModal({ show, onClose, event, onSuccess }) {
  const [formData, setFormData] = useState({});

  useEffect(() => {
    if (event) {
      setFormData(event);
    }
  }, [event]);

  if (!show || !event) return null;

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = () => {
    axios.put(`http://localhost:8080/api/evenements/${event.id}`, formData)
      .then(() => {
        onSuccess("✅ Événement modifié !");
        onClose();
      })
      .catch(() => alert("❌ Erreur modification"));
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center z-50">
      <div className="bg-white p-6 rounded w-[400px]">
        <h2 className="text-lg font-semibold mb-4">🛠 Modifier l’événement</h2>

        <input name="nom" value={formData.nom || ""} onChange={handleChange} placeholder="Nom" className="w-full mb-2 p-2 border rounded" />
        <input name="date" type="datetime-local" value={formData.date?.slice(0,16) || ""} onChange={handleChange} className="w-full mb-2 p-2 border rounded" />
        <input name="lieu" value={formData.lieu || ""} onChange={handleChange} placeholder="Lieu" className="w-full mb-2 p-2 border rounded" />
        <input name="capaciteMax" value={formData.capaciteMax || ""} onChange={handleChange} placeholder="Capacité" className="w-full mb-2 p-2 border rounded" />

        {formData.type === "conference" && (
          <>
            <input name="theme" value={formData.theme || ""} onChange={handleChange} placeholder="Thème" className="w-full mb-2 p-2 border rounded" />
            <input name="intervenants" value={formData.intervenants?.join(", ") || ""} onChange={(e) => setFormData(prev => ({
              ...prev,
              intervenants: e.target.value.split(",").map(s => s.trim())
            }))} placeholder="Intervenants (séparés par virgule)" className="w-full mb-2 p-2 border rounded" />
          </>
        )}

        {formData.type === "concert" && (
          <>
            <input name="artiste" value={formData.artiste || ""} onChange={handleChange} placeholder="Artiste" className="w-full mb-2 p-2 border rounded" />
            <input name="genreMusical" value={formData.genreMusical || ""} onChange={handleChange} placeholder="Genre musical" className="w-full mb-2 p-2 border rounded" />
          </>
        )}

        <div className="flex justify-end gap-2 mt-4">
          <button onClick={onClose} className="px-4 py-1 border rounded text-gray-700">Annuler</button>
          <button onClick={handleSubmit} className="px-4 py-1 bg-blue-600 text-white rounded">Enregistrer</button>
        </div>
      </div>
    </div>
  );
}
