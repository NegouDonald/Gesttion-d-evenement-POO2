// components/ParticipantList.jsx
import axios from "axios";

export default function ParticipantList({ participants, eventId, onUpdate }) {
  if (!participants || participants.length === 0) {
    return <p className="text-sm italic text-gray-500">Aucun participant inscrit.</p>;
  }

  const handleRemove = (participantId) => {
    if (window.confirm("Désinscrire ce participant ?")) {
      axios.delete(`http://localhost:8080/api/evenements/${eventId}/desinscrire/${participantId}`)
        .then(() => {
          alert("✅ Désinscription réussie");
          onUpdate();
        })
        .catch(() => alert("❌ Erreur lors de la désinscription"));
    }
  };

  return (
    <ul className="mt-2 space-y-1 text-sm">
        
      {participants.map((p, i) => (
        <li key={i} className="flex justify-between items-center gap-2 text-gray-700">
          <span>
            <span className="text-gray-500 font-mono">{i + 1}.</span> 👤
            <span className="font-medium"> {p.nom}</span> — <span>{p.email}</span>
          </span>
          <button
            onClick={() => handleRemove(p.id)}
            className="text-xs bg-red-100 text-red-700 px-2 py-0.5 rounded hover:bg-red-200"
          >
            ❌
          </button>
        </li>
      ))}
    </ul>
  );
}
