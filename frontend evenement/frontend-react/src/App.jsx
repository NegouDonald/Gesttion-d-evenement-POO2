import EventForm from './components/EventForm';
import EventList from './components/EventList';

function App() {
  return (
    <div className="min-h-screen bg-gray-100 text-gray-800 p-8">
      <div className="max-w-4xl mx-auto bg-white rounded-xl shadow-md p-6 space-y-8">
        <h1 className="text-3xl font-bold text-center">🎉 Gestion des Événements</h1>
        <EventForm />
        <EventList />
      </div>
    </div>
  );
}

export default App;
