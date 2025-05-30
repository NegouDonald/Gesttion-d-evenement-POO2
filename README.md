 

## 📄 `README.md`

````md
# 🎫 EventManager – Gestion d'Événements (Spring Boot + React)

Une application complète de gestion d’événements (conférences et concerts), avec une interface web moderne.

## 🚀 Fonctions principales

- Création d’événements : conférences 🎓 ou concerts 🎶
- Filtrage par type d’événement
- Inscription de participants avec capacité maximale
- Export/Import au format JSON et XML
- Affichage dynamique (modals, alertes, tags)
- Backend robuste avec validation et exceptions personnalisées

---

## 🧰 Technologies utilisées

### Backend
- Java 17
- Spring Boot 3.5.0
- JPA / Hibernate 
- Jackson + JAXB (JSON/XML)
- Lombok
- H2 (base mémoire) ou PostgreSQL
- Maven

### Frontend
- React 18 + Vite
- Axios
- TailwindCSS
- React Hooks

---

## 📦 Installation

### 1. Cloner le projet

```bash
 
 
````

---

## ⚙️ Backend (Spring Boot)

### 📁 Dossier : `/backend`

```bash
cd backend
```

### 🧪 Lancer le serveur

```bash
./mvnw spring-boot:run
```

* Accès : `http://localhost:8080`

 

## 💻 Frontend (React + Tailwind)

### 📁 Dossier : `/frontend`

```bash
cd  frontend evenement
npm install
npm run dev
```

* Accès : `http://localhost:5173`

---

## 🌐 API REST principales

| Méthode  | Endpoint                        | Description             |
| -------- | ------------------------------- | ----------------------- |
| `GET`    | `/api/evenements`               | Liste des événements    |
| `POST`   | `/api/evenements`               | Ajouter événement       |
| `DELETE` | `/api/evenements/{id}`          | Supprimer               |
| `POST`   | `/api/evenements/{id}/inscrire` | Inscrire un participant |
| `GET`    | `/api/fichier/export/json`      | Export JSON             |
| `GET`    | `/api/fichier/import/json`      | Import JSON             |
| `GET`    | `/api/fichier/export/xml`       | Export XML              |
| `GET`    | `/api/fichier/import/xml`       | Import XML              |

---

## 🎯 TODO / Prochaines étapes

* 🔒 Authentification (Admin / Utilisateur)
* 📊 Statistiques avec Java Streams
* 📥 Export CSV
* 🗂️ Tri et pagination côté React
* 📱 Responsive design mobile

---

## 👨‍💻 Auteur

Projet réalisé par **TAMEGUE NEGOU  DONALD**
🎓 Étudiant 3GI polytechnique Yaounde
📊 Encadreur en Mathematique informatique & Coach  en trading
✉️ Contact : tameguedonald@gmail.com
Telephone : 00237690914045

---
