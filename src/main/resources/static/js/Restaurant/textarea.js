  function xSize(e)
    {
        var xe = document.getElementById('xt'),
            t;

        e.onfocus = function()
        {
            t = setInterval(
                function()
                {
                    xe.value = e.value;
                    e.style.height = (xe.scrollHeight + 12) + 'px';
                }, 100);
        }

        e.onblur = function()
        {
            clearInterval(t);
        }
    }

    xSize(document.getElementById('ta'));