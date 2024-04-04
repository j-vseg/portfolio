var cat;
var tail;
var shadow;
var leftEar;
var leftEarShadow;
var rightEar;
var rightEarShadow;
var tails=[];
var commandList=["m","M","c","C","s","S"];
var index=1;
window.onload = function () {
    cat= document.getElementById("cat");
    tail=cat.getElementById("tail1");
    shadow=cat.getElementById("tailShadow");
    leftEar=cat.getElementById("leftEar");
    leftEarShadow=cat.getElementById("leftEarShadow");
    rightEar=cat.getElementById("rightEar");
    rightEarShadow=cat.getElementById("rightEarShadow");
    tails[0]=parsePath(cat.getElementById("tail1").getAttribute("d"));
    tails[1]=parsePath(cat.getElementById("tail2").getAttribute("d"));
    tails[2]=parsePath(cat.getElementById("tail3").getAttribute("d"));
    tails[3]=parsePath(cat.getElementById("tail4").getAttribute("d"));
    tweenTail();
    tweenEar("left");
    tweenEar("right");
    var inputvalue=-34;
    var positive=false;
    var base=50;
};
function tweenTail(){
    var targetData=tails[index];
    var duration=Math.random()+1.5;
    for(var i=0;i<targetData.length;i++){
        var tweenObject=targetData[i].original;
        tweenObject.delay=.3*(2-i);
        tweenObject.ease=Cubic.easeInOut;
        tweenObject.onUpdate=updatePath;
        TweenMax.killTweensOf(tails[0][i].current);
        TweenMax.to(tails[0][i].current,duration-(tweenObject.delay*.25),tweenObject)
    }
    TweenMax.delayedCall(duration,tweenTail);
    index++;
    if(index==tails.length){
        index=0;
    }
}
function tweenEar(direction){
    TweenMax.to([this[direction+"Ear"],this[direction+"EarShadow"]],(Math.random()*.5)+.3,{css:{rotation:(Math.random()*20)-15,transformOrigin:"30% 100%"},delay:(Math.random()*.5)+.6,ease:Quart.easeInOut,onComplete:tweenEar,onCompleteParams:[direction],onCompleteScope:this});
}

function updatePath(){
    var string="";
    for(var i=0;i<tails[0].length;i++){
        var point=tails[0][i];
        var command=point.command;
        string+=command;
        string+=point.current;
    }
    tail.setAttribute("d",string);
    shadow.setAttribute("d",string);
}
function parsePath(path){
    var points=[];
    path=addCommas(path);
    for(var i=0;i<path.length;i++){
        var nextIndex=getNextIndex(path,i);
        var string=path.substring(i,nextIndex);
        var command=string.substring(0,1);
        var data=string.substring(1);
        var dataArray=data.split(",");
        for(var d=0;d<dataArray.length;d++){
            dataArray[d]=parseFloat(dataArray[d]);
        }
        var pathData={command:command,current:dataArray.slice(),original:dataArray.slice()}

        points.push(pathData);
        i=nextIndex-1;
    }
    return points;
}
function addCommas(string){
    string=string.replace(/-/g,",-");
    string=string.replace(/,,/g,",");
    for(var i=0;i<commandList.length;i++){
        var command=commandList[i];
        var reg=new RegExp(command+",","g");
        string=string.replace(reg,command);
    }
    return string;
}
function getNextIndex(string,startIndex){
    var nextIndex=string.length;
    for(var i=0;i<commandList.length;i++){
        var command=commandList[i];
        var index=string.indexOf(command,startIndex+1);
        if(index<nextIndex&&index!=-1){
            nextIndex=index;
        }
    }
    return nextIndex;
}

// var lastScroll = 0;
// var isScrolling = false;

// function updateCatAnimation(){
//   const {scrollTop} =  document.documentElement;
//   const headerWidth = document.getElementById('header').offsetWidth;
//   const headerHeight = document.getElementById('header').offsetHeight;
//   const scrollPercent = (scrollTop / headerHeight);
//   const right = (headerWidth * scrollPercent) + 'px';

//   if ((Math.round(lastScroll * 100) / 100) != 
//     (Math.round(scrollPercent * 100) / 100)) {
//     document.querySelector('#header-cat-animation').style.setProperty('--progress', right);
    
//     if (!isScrolling) {
//       isScrolling	= true;
//     }
//   }
//   else {
//     isScrolling = false;
//   }

//   lastScroll = scrollPercent;
// }
// document.addEventListener('scroll', updateCatAnimation);

// // Animations
// const animationStates = [
//   {
//       name: 'walking',
//       frames: 12,
//   },
//   {
//       name: 'sitting',
//       frames: 6,
//   },
//   {
//       name: 'walk-then-sit',
//       frames: 12,
//   },
// ];

// const spriteAnimations = ['walking', 'sitting', 'walk-then-sit'];

// // Header Cat Animation
// const headerCanvas = document.getElementById('header-cat-animation');
// const headerCTX = headerCanvas.getContext('2d');
// const headerCanvas_WIDTH = headerCanvas.width = 200;
// const headerCanvas_HEIGHT = headerCanvas.height = 100;
// const spriteWidth = 200;
// const spriteHeight = 100;
// let headerGameFrame = 0;
// let headerStagger = 0;
// const headerStaggerFrame = 5;
// let headerIndex = 3;

// const catHeaderImg = document.getElementById('catHeaderImg');

// function animateHeaderCat(){
//   if((headerStagger % headerStaggerFrame) == 0){
//     headerCTX.clearRect(0 , 0, headerCanvas_WIDTH, headerCanvas_HEIGHT);
//     headerIndex = spriteAnimations.indexOf('walking');
//     let frameX = spriteWidth * headerIndex;
//     let frameY = spriteHeight * (headerGameFrame % (animationStates[headerIndex].frames));
//     headerCTX.drawImage(catHeaderImg, frameX, frameY, spriteWidth, spriteHeight,
//       0, 0, headerCanvas_WIDTH, headerCanvas_HEIGHT);
    
//     if (isScrolling) {
//       headerGameFrame++; 
//     }
//   }
//   headerStagger++;
    
//   requestAnimationFrame(animateHeaderCat);
// }
// animateHeaderCat();

// // Quote Cat Animation
// const quoteCanvas = document.getElementById('quote-cat-animation');
// const quoteCTX = quoteCanvas.getContext('2d');
// const quoteCanvas_WIDTH = quoteCanvas.width = 200;
// const quoteCanvas_HEIGHT = quoteCanvas.height = 100;
// let quoteGameFrame = 0;
// let quoteStagger = 0;
// const quoteStaggerFrame = 5;
// let quoteIndex = 3;

// const catQuoteImg = document.getElementById('catQuoteImg');

// function animateQuoteCat(){
//   if((quoteStagger % quoteStaggerFrame) == 0){
//     quoteCTX.clearRect(0 , 0, quoteCanvas_WIDTH, quoteCanvas_HEIGHT);
//     quoteIndex = spriteAnimations.indexOf('sitting');
//     let frameX = spriteWidth * quoteIndex;
//     let frameY = spriteHeight * (quoteGameFrame % (animationStates[quoteIndex].frames));
//     quoteCTX.drawImage(catQuoteImg, frameX, frameY, spriteWidth, spriteHeight,
//       0, 0, quoteCanvas_WIDTH, quoteCanvas_HEIGHT);
    
//     if (isScrolling) {
//       quoteGameFrame++; 
//     }
//   }
//   quoteStagger++;
    
//   requestAnimationFrame(animateQuoteCat);
// }
// animateQuoteCat();