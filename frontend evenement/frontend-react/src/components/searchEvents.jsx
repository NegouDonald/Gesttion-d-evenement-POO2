const searchEvents = () => {
    if (searchTerm.trim() === "") {
      fetchEvents(); // Si champ vide → recharger tout
      return;
    }
  
    axios.get(`http://localhost:8080/api/evenements/search?q=${searchTerm}`)
      .then(response => setEvents(response.data))
      .catch(error => {
        console.error("Erreur de recherche", error);
        setMessage("❌ Aucun événement trouvé.");
      });
  };
  