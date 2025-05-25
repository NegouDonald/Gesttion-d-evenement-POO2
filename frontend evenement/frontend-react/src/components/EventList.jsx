import { useEffect, useState } from "react";
import axios from "axios";
import { ExportButtons } from "./ExportButtons";

export default function EventList() {
  const [events, setEvents] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/api/evenements")
      .then(response => setEvents(response.data))
      .catch(error => console.error("Erreur chargement événements", error));
  }, []);

  return (
    <div className="space-y-2">
      <h2 className="text-xl font-semibold">Liste des événements</h2>
      <ExportButtons />
      {events.length === 0 ? (
        <p className="text-gray-500 italic">Aucun événement trouvé.</p>
      ) : (
        <ul className="divide-y divide-gray-200">
          {events.map((event, idx) => (
            <li key={idx} className="py-2">
              <span className="font-semibold">{event.nom}</span> — {event.type} — {event.date}
            </li>
          ))}
        </ul>
      )}

<ul className="divide-y divide-gray-200">
  {events.map((event, idx) => (
    <li key={idx} className="py-3">
      <div className="font-bold text-blue-600">{event.nom}</div>
      <div className="text-sm text-gray-700">
        📅 {event.date} — 📍 {event.lieu} — 👥 {event.capaciteMax} places
      </div>
      {event.type === "conference" && (
        <div className="mt-1">
          <span className="text-gray-600">🎓 Thème : </span>{event.theme} <br />
          <span className="text-gray-600">🎤 Intervenants : </span>{event.intervenants.join(", ")}
        </div>
      )}
      {event.type === "concert" && (
        <div className="mt-1">
          <span className="text-gray-600">🎤 Artiste : </span>{event.artiste} <br />
          <span className="text-gray-600">🎶 Genre : </span>{event.genreMusical}
        </div>
      )}
    </li>
  ))}
</ul>

    </div>
  );
}
