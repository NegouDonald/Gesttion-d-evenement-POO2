import { useState, useEffect } from "react";
import axios from "axios";

export default function EventForm({ selectedEvent, onSuccess }) {
  const [formData, setFormData] = useState({
    type: "conference",
    nom: "",
    date: "",
    lieu: "",
    capaciteMax: 0,
    theme: "",
    intervenants: ""
  });

  const [message, setMessage] = useState(null);
  const [isSuccess, setIsSuccess] = useState(true);
  const [isEditing, setIsEditing] = useState(false);

  // Charger les données si on est en mode édition
  useEffect(() => {
    if (selectedEvent) {
      setFormData({
        ...selectedEvent,
        intervenants: selectedEvent.intervenants?.join(", ") || "",
        date: selectedEvent.date?.slice(0, 16) || "", // format pour datetime-local
      });
      setIsEditing(true);
    }
  }, [selectedEvent]);

  const handleChange = (e) =>
    setFormData((prev) => ({ ...prev, [e.target.name]: e.target.value }));

  const handleSubmit = async (e) => {
    e.preventDefault();
    const data = {
      ...formData,
      capaciteMax: parseInt(formData.capaciteMax),
      intervenants: formData.intervenants
        ? formData.intervenants.split(",").map((i) => i.trim())
        : [],
    };

    try {
      if (isEditing) {
        await axios.put(
          `http://localhost:8080/api/evenements/${selectedEvent.id}`,
          data
        );
        setMessage("✅ Événement modifié avec succès !");
      } else {
        await axios.post("http://localhost:8080/api/evenements", data);
        setMessage("✅ Événement ajouté avec succès !");
      }
      setIsSuccess(true);
      onSuccess && onSuccess(); // callback parent
    } catch (error) {
      const msg =
        error.response?.data?.message || "❌ Erreur lors de l’enregistrement";
      setMessage(msg);
      setIsSuccess(false);
    }
  };

  return (
    <form
      onSubmit={handleSubmit}
      className="space-y-6 p-6 bg-white shadow-md rounded-lg"
    >
      <h2 className="text-2xl font-bold text-gray-800">
        {isEditing ? "✏️ Modifier un événement" : "➕ Ajouter un événement"}
      </h2>

      {message && (
        <div
          className={`text-sm px-4 py-2 rounded ${
            isSuccess
              ? "bg-green-100 text-green-800"
              : "bg-red-100 text-red-700"
          }`}
        >
          {message}
        </div>
      )}
<div>
  <label className="block mb-1 font-medium">Type d’événement :</label>
  <select
    name="type"
    value={formData.type}
    onChange={handleChange}
    className="w-full border border-gray-300 p-3 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
  >
    <option value="conference">🎓 Conférence</option>
    <option value="concert">🎶 Concert</option>
  </select>
</div>

      <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
        <input
          name="nom"
          placeholder="Nom"
          value={formData.nom}
          onChange={handleChange}
          required
          className="border p-3 rounded-md"
        />
        <input
          name="date"
          type="datetime-local"
          value={formData.date}
          onChange={handleChange}
          required
          className="border p-3 rounded-md"
        />
        <input
          name="lieu"
          placeholder="Lieu"
          value={formData.lieu}
          onChange={handleChange}
          required
          className="border p-3 rounded-md"
        />
        <input
          name="capaciteMax"
          type="number"
          value={formData.capaciteMax}
          onChange={handleChange}
          required
          className="border p-3 rounded-md"
        />
        {formData.type === "conference" && (
  <>
    <input
      name="theme"
      placeholder="Thème de la conférence"
      onChange={handleChange}
      className="border border-gray-300 p-3 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
    />

    <input
      name="intervenants"
      placeholder="Intervenants (séparés par virgules)"
      onChange={handleChange}
      className="border border-gray-300 p-3 rounded-md col-span-1 md:col-span-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
    />
  </>
)}

{formData.type === "concert" && (
  <>
    <input
      name="artiste"
      placeholder="Nom de l'artiste"
      onChange={handleChange}
      className="border border-gray-300 p-3 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
    />

    <input
      name="genreMusical"
      placeholder="Genre musical"
      onChange={handleChange}
      className="border border-gray-300 p-3 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
    />
  </>
)}

        {formData.type === "conference" && (
          <>
            <input
              name="theme"
              placeholder="Thème"
              value={formData.theme}
              onChange={handleChange}
              className="border p-3 rounded-md"
            />
            <input
              name="intervenants"
              placeholder="Intervenants (séparés par virgules)"
              value={formData.intervenants}
              onChange={handleChange}
              className="border p-3 rounded-md md:col-span-2"
            />
          </>
        )}
      </div>

      <div className="flex justify-end">
        <button
          type="submit"
          className="bg-blue-600 text-white px-6 py-3 rounded hover:bg-blue-700"
        >
          {isEditing ? "✅ Modifier" : "➕ Ajouter"}
        </button>
      </div>
    </form>
  );
}
