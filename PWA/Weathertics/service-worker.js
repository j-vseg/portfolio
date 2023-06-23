const CACHE_VERSION = 4;
const CACHE_NAME = `CACHE-V${CACHE_VERSION}`;

let lat = 0.0;
let long = 0.0;
/*if (localStorage.getItem("locationLat") !== null && localStorage.getItem("locationLong") !== null) {
  lat = localStorage.getItem("locationLat");
  long = localStorage.getItem("locationLong");
}*/

const cachedFiles = [
  "js/scripts.js",
  "offline.html",
  "css/templates/layout.css",
  "css/top-bar.css",
  "css/offline.css",
  "images/logo.png",
  "images/profile-picture.jpg",
  "images/offline-image.png",
  "https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css",
  "https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js",
  "https://kit.fontawesome.com/ecef4a84e2.js",
  "https://fonts.gstatic.com/s/poppins/v20/pxiEyp8kv8JHgFVrJJfecg.woff2",
  "https://fonts.googleapis.com/css?family=Poppins"
];

self.addEventListener("install",(installing)=>{
  installing.waitUntil(
    caches.open(CACHE_NAME).then((cache)=>{
      return cache.addAll(cachedFiles);
    })
  );
  });
  
  self.addEventListener("activate",(activating)=>{	
    /*activating.waitUntil(
      caches.keys().then(cacheNames => {
        return Promise.all(
          cacheNames.map(cacheName => {
            if (cacheName !== CACHE_NAME) {
              return caches.delete(cacheName);
            }
          })
        );
      })
    )*/
  });
  
  self.addEventListener("fetch",(fetching)=>{   
    fetching.respondWith(
      caches.match(fetching.request)
      .then(response => response || fetch(fetching.request))
        .catch(() => {   
          if (fetching.request.mode == 'navigate') {
            return caches.match('offline.html');
          }
        })
    );
  });
  
  self.addEventListener("push",(pushing)=>{
      console.log("Service Worker: I received some push data, but because I am still very simple I don't know what to do with it :(");
  })
  