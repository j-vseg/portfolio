var typed = new Typed('#title_name', {
  strings: ['I am <span class="ocean-green">Janne van Seggelen</span>', 'and I am a <span class="ocean-green">Software Engineer</span>'],
  typeSpeed: 100,
  backSpeed: 100,
  loop: true
});

var navbar = document.getElementById("navbar");
window.addEventListener('scroll', () => {
  if (window.scrollY >= 650) { navbar.classList.add('navbar-scrolled'); }
  else if (window.scrollY < 650) { navbar.classList.remove('navbar-scrolled'); }
})

const tween = KUTE.fromTo(
  '#layer1',
  { path: '#layer1' },
  { path: '#layer2' },
  { repeat: 999, duration: 3000, yoyo: true }
).start();
const tween2 = KUTE.fromTo(
  '#layer2',
  { path: '#layer2' },
  { path: '#layer3' },
  { repeat: 999, duration: 3000, yoyo: true }
).start();
const tween3 = KUTE.fromTo(
  '#layer3',
  { path: '#layer3' },
  { path: '#layer4' },
  { repeat: 999, duration: 3000, yoyo: true }
).start();
const tween4 = KUTE.fromTo(
  '#layer4',
  { path: '#layer4' },
  { path: '#layer5' },
  { repeat: 999, duration: 3000, yoyo: true }
).start();
const tween5 = KUTE.fromTo(
  '#layer5',
  { path: '#layer5' },
  { path: '#layer4' },
  { repeat: 999, duration: 3000, yoyo: true }
).start();