var typed = new Typed('#title_name', {
  strings: ['I am <span class="light-blue">Janne van Seggelen</span>', 'and I am a <span class="light-blue">Software Engineer</span>'],
  typeSpeed: 100,
  backSpeed: 100,
  loop: true
});

var navbar = document.getElementById("navbar");
window.addEventListener('scroll', () => {
  if (window.scrollY >= 650) { navbar.classList.add('navbar-scrolled'); }
  else if (window.scrollY < 650) { navbar.classList.remove('navbar-scrolled'); }
})

var docTitle = document.title;
window.addEventListener("blur", () => {
  document.title = "Come back :c"
})
window.addEventListener("focus", () => {
  document.title = docTitle
})