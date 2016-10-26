(function (window, document) {

    "use strict";

    const canvas = document.getElementById("canvas");
    const ctxt = canvas.getContext("2d");

    window.draw = {
        
        drawRect({ position = [0, 0], size = [1, 1], color = [0, 0, 0] }) {
        
            const [x, y] = position;
            const [width, height] = size;
            const [r, g, b] = color;

            ctxt.fillStyle = `rgb(${r}, ${g}, ${b})`;
            ctxt.fillRect(x, y, width, height);
        
        }
    
    };


} (this, this.document));
