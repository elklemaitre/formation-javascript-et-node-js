// Créer le tableau de paires mélangées
const tab1 = Array.from({ length: 12 }, (_, i) => i); // [0, 1, 2, ..., 11]
const tab2 = melanger(tab1.concat(tab1)); // Doublé et mélangé

// Fonction pour mélanger un tableau
function melanger(tab) {
  let tab2 = [];
  for (let i = 0; i < tab.length; i++) {
    let x;
    do {
      x = Math.floor(Math.random() * tab.length);
    } while (tab2[x] != undefined);
    tab2[x] = tab[i];
  }
  return tab2;
}

// Générer la grille de tuiles dans le conteneur
const container = document.querySelector('.container');

tab2.forEach((val) => {
  const tile = document.createElement('div');
  tile.classList.add('tile');
  tile.innerHTML = `<img src="img/${val}.webp" alt="Tuile ${val}" />`;
  container.appendChild(tile);
});

let firstTile = null;
let secondTile = null;
let isChecking = false;

container.addEventListener('click', (event) => {
  const clickedTile = event.target.closest('.tile');

  // Éviter les erreurs (clic sur un espace vide ou une tuile déjà trouvée)
  if (!clickedTile || clickedTile.classList.contains('matched') || isChecking) return;

  // Révéler la tuile
  clickedTile.classList.add('flipped');

  if (!firstTile) {
    firstTile = clickedTile;
  } else if (!secondTile) {
    secondTile = clickedTile;

    // Vérifier la correspondance
    isChecking = true;
    setTimeout(() => {
      if (firstTile.innerHTML === secondTile.innerHTML) {
        firstTile.classList.add('matched');
        secondTile.classList.add('matched');
      } else {
        firstTile.classList.remove('flipped');
        secondTile.classList.remove('flipped');
      }

      // Réinitialiser
      firstTile = null;
      secondTile = null;
      isChecking = false;

      // Vérifier la victoire
      if (document.querySelectorAll('.matched').length === tab2.length) {
        alert('Félicitations, vous avez gagné !');
        stopTimer();
      }
    }, 1000);
  }
});

let seconds = 0;
let timer = setInterval(() => {
  seconds++;
  document.querySelector('.timer').textContent = `Temps écoulé : ${seconds}s`;
}, 1000);

// Arrêtez le chronomètre lorsque toutes les paires sont trouvées
function stopTimer() {
  clearInterval(timer);
}
