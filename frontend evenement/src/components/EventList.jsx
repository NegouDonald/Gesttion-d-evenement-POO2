import { useEffect, useState } from "react";
import axios from "axios";
import InscriptionModal from "../components/InscriptionModal";
import ParticipantList from "../components/ParticipantList";
import EditEventModal from "../components/EditEventModal";


export default function EventList() {
  const [events, setEvents] = useState([]);
  const [selectedType, setSelectedType] = useState("all");
  const [message, setMessage] = useState(null);
  const [modalOpen, setModalOpen] = useState(false);
  const [selectedEventId, setSelectedEventId] = useState(null);
  const [searchTerm, setSearchTerm] = useState("");
  const [editModalOpen, setEditModalOpen] = useState(false);
const [eventToEdit, setEventToEdit] = useState(null);


  useEffect(() => {
    fetchEvents();
  }, []);

  const fetchEvents = () => {
    axios.get("http://localhost:8080/api/evenements")
      .then((res) => setEvents(res.data))
      .catch(() => setMessage("❌ Erreur de chargement"));
  };

  const searchEvents = () => {
    if (searchTerm.trim() === "") {
      fetchEvents();
      return;
    }

    axios.get(`http://localhost:8080/api/evenements/search?q=${searchTerm}`)
      .then(res => setEvents(res.data))
      .catch(() => setMessage("❌ Aucun événement trouvé"));
  };

  const handleDelete = (id) => {
    if (window.confirm("Supprimer ?")) {
      axios.delete(`http://localhost:8080/api/evenements/${id}`)
        .then(() => {
          setMessage("✅ Événement supprimé !");
          fetchEvents();
        })
        .catch(() => setMessage("❌ Échec suppression"));
    }
  };

  const openInscription = (id) => {
    setSelectedEventId(id);
    setModalOpen(true);
  };

  const filteredEvents = selectedType === "all"
    ? events
    : events.filter(event => event.type === selectedType);

  return (
    <div className="p-4">
      <h2 className="text-xl font-bold mb-4">🎫 Événements</h2>

      {message && (
        <div className="mb-3 text-sm px-4 py-2 rounded bg-green-100 text-green-800">{message}</div>
      )}

      {/* 🔍 Recherche */}
      <div className="mb-4 flex gap-2">
        <input
          type="text"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          onKeyDown={(e) => {
            if (e.key === "Enter") searchEvents();
          }}
          placeholder="🔍 Rechercher par nom..."
          className="px-3 py-1 border rounded w-full"
        />
        <button
          onClick={searchEvents}
          className="bg-blue-600 text-white px-4 py-1 rounded hover:bg-blue-700 text-sm"
        >
          Rechercher
        </button>
      </div>

      {/* Filtres */}
      <div className="flex items-center gap-2 mb-4">
        <label>Filtrer :</label>
        <select
          value={selectedType}
          onChange={(e) => setSelectedType(e.target.value)}
          className="px-2 py-1 border rounded"
        >
          <option value="all">Tous</option>
          <option value="conference">Conférences</option>
          <option value="concert">Concerts</option>
        </select>
      </div>

      {/* Liste */}
      <ul className="space-y-4">
        {filteredEvents.map((event) => (
          <li key={event.id} className="border p-4 rounded shadow-sm">
            <div className="flex justify-between items-start">
              <div>
                <h3 className="text-lg font-semibold text-blue-700">{event.nom}</h3>
                <span className={`text-xs font-medium px-2 py-1 rounded-full ${
                  event.type === "concert" ? "bg-purple-100 text-purple-800" : "bg-blue-100 text-blue-800"
                }`}>
                  {event.type === "concert" ? "🎶 Concert" : "🎓 Conférence"}
                </span>
                <p className="text-sm text-gray-700 mt-1">
                  📅 {event.date} — 📍 {event.lieu} — 👥 {event.participants.length} inscrit{event.participants.length > 1 ? "s" : ""} / {event.capaciteMax} max
                </p>

                {event.type === "conference" && (
                  <>
                    <p><strong>Thème :</strong> {event.theme}</p>
                    <p><strong>Intervenants :</strong> {event.intervenants.join(", ")}</p>
                  </>
                )}
                {event.type === "concert" && (
                  <>
                    <p><strong>Artiste :</strong> {event.artiste}</p>
                    <p><strong>Genre :</strong> {event.genreMusical}</p>
                  </>
                )}

                {/* 👥 Liste des participants */}
                <div className="mt-3">
                  <h4 className="font-semibold text-sm">👥 Participants :</h4>
                  <ParticipantList
                    participants={event.participants}
                    eventId={event.id}
                    onUpdate={fetchEvents}
                  />
                </div>
              </div>

              <div className="flex flex-col items-end gap-2">
                <button
                  onClick={() => openInscription(event.id)}
                  className="bg-green-100 text-green-800 px-3 py-1 rounded hover:bg-green-200 text-sm"
                >
                  ➕ S'inscrire
                </button>
                <button
                  onClick={() => handleDelete(event.id)}
                  className="bg-red-100 text-red-800 px-3 py-1 rounded hover:bg-red-200 text-sm"
                >
                  🗑️ Supprimer
                </button>
                <button
  onClick={() => {
    setEventToEdit(event);
    setEditModalOpen(true);
  }}
  className="bg-yellow-100 text-yellow-800 px-3 py-1 rounded hover:bg-yellow-200 text-sm"
>
  ✏️ Modifier
</button>

              </div>
            </div>
          </li>
        ))}
      </ul>

      {/* 📦 Modal d'inscription */}
      <InscriptionModal
        show={modalOpen}
        onClose={() => setModalOpen(false)}
        eventId={selectedEventId}
        onSuccess={(msg) => {
          setMessage(msg);
          fetchEvents();
        }}
      />

<EditEventModal
  show={editModalOpen}
  onClose={() => setEditModalOpen(false)}
  event={eventToEdit}
  onSuccess={(msg) => {
    setMessage(msg);
    fetchEvents();
  }}
/>

    </div>
  );
}
