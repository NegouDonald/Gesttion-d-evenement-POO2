import { useState } from "react";
import axios from "axios";

export default function EventForm() {
  const [formData, setFormData] = useState({
    type: "conference",
    nom: "",
    date: "",
    lieu: "",
    capaciteMax: 0,
    theme: "",
    intervenants: ""
  });

  const handleChange = (e) => setFormData({ ...formData, [e.target.name]: e.target.value });

  const handleSubmit = (e) => {
    e.preventDefault();
    const data = {
      ...formData,
      capaciteMax: parseInt(formData.capaciteMax),
      intervenants: formData.intervenants.split(",").map(i => i.trim())
    };

    axios.post("http://localhost:8080/api/evenements", data)
      .then(() => alert("✅ Événement ajouté avec succès !"))
      .catch(err => console.error("Erreur :", err));
  };

  return (
    <form onSubmit={handleSubmit} className="space-y-4">
      <h2 className="text-xl font-semibold">Ajouter un événement</h2>
      <div className="grid grid-cols-2 gap-4">
        <input name="nom" placeholder="Nom" onChange={handleChange} required className="input" />
        <input name="date" type="datetime-local" onChange={handleChange} required className="input" />
        <input name="lieu" placeholder="Lieu" onChange={handleChange} required className="input" />
        <input name="capaciteMax" type="number" onChange={handleChange} required className="input" />
        <input name="theme" placeholder="Thème" onChange={handleChange} className="input" />
        <input name="intervenants" placeholder="Intervenants (séparés par ,)" onChange={handleChange} className="input col-span-2" />
      </div>
      <button type="submit" className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700">
        ➕ Soumettre
      </button>
    </form>
  );
}
