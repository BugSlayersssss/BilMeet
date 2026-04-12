# BilMeet 🎓🤝

[cite_start]BilMeet is a desktop social synchronization hub designed to solve the constant "When are you free?" struggle for Bilkent University students[cite: 3, 4]. [cite_start]Unlike traditional social media, BilMeet focuses on turning dead hours between classes into opportunities for meaningful connection by matching academic schedules with personal interests[cite: 4, 7].

[cite_start]This project was developed as part of CS102 – Algorithms and Programming II (Spring 2025/2026) by the Bug Slayers[cite: 270].

---

## Project Overview

[cite_start]The primary goal of BilMeet is to help Bilkent students coordinate social and productive activities during their free hours between classes[cite: 4, 5]. [cite_start]Since checking availability manually can be time-consuming, BilMeet automates this process by combining schedule matching with interest compatibility[cite: 4]. [cite_start]The platform is exclusive to Bilkent students, encouraging a safer and more community-focused environment[cite: 342, 348].

---

## System Features

### User Management
* [cite_start]Account creation is restricted to official university email addresses[cite: 342, 348].
* [cite_start]Profiles include details such as name, department, and specific interest tags [cite: 47-52].
* [cite_start]Users can customize their presence with color-coded avatars[cite: 363].

### Schedule and Calendar
* [cite_start]The interactive calendar is the core of BilMeet's synchronization[cite: 372].
* [cite_start]Users can manually enter their class schedules and edit their availability at any time[cite: 104, 374].
* [cite_start]The system provides a weekly calendar view with hourly availability blocks [cite: 234-263].

### Hangout Request System
* [cite_start]Users can create hangout requests by specifying the event name, location, time, and participant quota [cite: 74-80, 385-389].
* [cite_start]Requests include activity tags to help interested students find specific events[cite: 81, 387].
* [cite_start]The system manages quota checks and participant lists for every event[cite: 80, 85].

### Messaging System
* [cite_start]Real-time communication is supported through private messaging between friends[cite: 376].
* [cite_start]Event-specific group chats are automatically available for all participants of a session[cite: 377].
* [cite_start]Full conversation history is maintained for both private and group interactions[cite: 26, 293].

### Smart Matching and Search
* [cite_start]A specialized algorithm calculates match scores based on interest compatibility[cite: 34, 35].
* [cite_start]The search tab allows users to discover others, with results sorted by how well their interests align[cite: 36, 367].

---

## Tech Stack

* [cite_start]Language: Java [cite: 3]
* [cite_start]GUI Framework: JavaFX (designed with Scene Builder) [cite: 3, 326]
* [cite_start]Database: Firebase (for real-time data and authentication) [cite: 3, 317]
* [cite_start]Build Tool: Maven [cite: 332]
* [cite_start]Version Control: GitKraken and GitHub [cite: 301, 302]

---

## The Team (Bug Slayers)

[cite_start]Initial work was distributed as follows, though the final stage involved collective integration[cite: 294]:

* [cite_start]Nehir Bakdur: Social and Friendship System (Search, Friend Requests) [cite: 286-289].
* [cite_start]Asmin Deniz Kılıçoğlu: Event and Hangout System (Core Backend Algorithms) [cite: 282-285].
* [cite_start]Onur Balcı: Messaging System (Private and Group Chats) [cite: 290-293].
* [cite_start]İpek Terzioğlu: Authentication and Main Flow (Login, Navigation) [cite: 272-277].
* [cite_start]Leena Nasser Al Hroub: Data Management, Profile, and Schedule Logic [cite: 278-281].

---

## Installation

1. [cite_start]Download and install Maven from the official website[cite: 333].
2. [cite_start]Add Maven to your system environment variables[cite: 334].
3. [cite_start]Clone the repository and open it in your IDE[cite: 335, 336].
4. [cite_start]Under the Maven tab, navigate to plugins, select javafx, and click run [cite: 337-339].

