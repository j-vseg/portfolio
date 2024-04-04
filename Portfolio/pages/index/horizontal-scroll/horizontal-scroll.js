if (document.getElementById('body').offsetWidth >= 768) {
  gsap.registerPlugin(ScrollTrigger);

  const panels = document.querySelector('.panels');

  function getScrollAmount() {
    let panelsWidth = panels.offsetWidth;
    let panelTotalPadding = 1 * (2 * 64);
    return -((panelsWidth + panelTotalPadding) - window.innerWidth);
  }

  const tween = gsap.to(panels, {
    x: getScrollAmount,
    duration: 3, 
    ease: 'none'
  });

  ScrollTrigger.create({
    trigger: '.horizontal-scroll',
    start: 'top', //'top 20%',
    end: () => `+=${getScrollAmount() * -1}`,
    pin: true, 
    animation: tween, 
    scrub: 1, 
    invalidateOnRefresh: true //,
    //markers: true
  });
}
else {
  document.getElementById('horizontal-scroll').style.display = 'none';
  document.getElementById('horizontal-scroll-sm').style.display = 'block';
}

// Draggable scroll
gsap.registerPlugin(Draggable);

let slides = gsap.utils.toArray(".slide"),
    contents = gsap.utils.toArray(".content-box"),
    currentSlide = 5,
    snapPoints,
    getSlideIndexAt = x => snapPoints.indexOf(gsap.utils.snap(snapPoints, x));

let draggable = Draggable.create(".slides-container", {
  type: "x",
  onDragEnd() {
    updateSlide(this.endX);
  },
  inertia: true,
  snap: {
    x: (value) => gsap.utils.snap(snapPoints, value)
  }
})[0];

function onResize() {
  let centerX = window.innerWidth / 2;
  snapPoints = slides.map(el => {
    let bounds = el.getBoundingClientRect();
    return centerX - (bounds.left + bounds.width / 2) + draggable.x;
  });
  draggable.applyBounds({
    minX: snapPoints[snapPoints.length - 1],
    maxX: snapPoints[0]
  })
}
onResize();
window.addEventListener("resize", onResize);

function updateSlide(x) {
  const newSlide = getSlideIndexAt(x);
  if (newSlide !== currentSlide) {
    contents[currentSlide].style.display = 'none';
    contents[newSlide].style.display = 'block';

    slides[currentSlide].style.animation = 'none';
    slides[newSlide].style.animation = 'flicker 4s ease-in-out infinite';
    currentSlide = newSlide;
  }
}
