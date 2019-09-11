import React from 'react';
import './App.css';

function App() {
  const [people, setPeople] = React.useState([])

  React.useEffect(async () => {
    const data = await fetch('/api/people').then(response => response.json())
    console.log(data._embedded.people)
    setPeople(data._embedded.people)
    console.log()
  }, [])

  return (
    <div className="App">
      <header className="App-header">
        {(people === undefined || people.length == 0) ? 'There are no people.' : 'There are people!'}
        <ul>
        { people.map(person => (
          <li key={person.id}>
            {person.name}
          </li>
        )) }
        </ul>
      </header>
    </div>
  );
}

export default App;
