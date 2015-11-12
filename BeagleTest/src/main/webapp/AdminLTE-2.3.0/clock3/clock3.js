var previousDate;
function middle(){

      wrapTop = ($(window).height() - $('#wrap').height())/2;
      wrapLeft = ($(window).width() - $('#wrap').width())/2;

      $('#wrap').animate({marginTop: wrapTop, marginLeft: wrapLeft}, 500);

    };

    
    function checktime(prevhour,prevmins,prevsecs){

        var now = new Date();

        var hour = now.getHours();
        if(hour < 10) hour = "0" + hour;

        var mins = now.getMinutes();
        if(mins < 10) mins = "0" + mins;

  	var secs = now.getSeconds();
  	if(secs < 10) secs = "0" + secs;  

        var hour = hour + "";
        var mins = mins + "";
  	var secs = secs + "";

        if(prevhour != hour) {

          var prevhour = prevhour + ""
          var hoursplit = hour.split("");
          var prevhoursplit = prevhour.split("");

          if(prevhoursplit[0] != hoursplit[0]) numberflip('#hourl',hoursplit[0]);
          if(prevhoursplit[1] != hoursplit[1]) numberflip('#hourr',hoursplit[1]);

        };

        if(prevmins != mins) {

          var prevmins = prevmins + ""
          var minsplit = mins.split("");
          var prevminsplit = prevmins.split("");

          if(prevminsplit[0] != minsplit[0]) numberflip('#minl',minsplit[0]);
          if(prevminsplit[1] != minsplit[1]) numberflip('#minr',minsplit[1]);

        };

        if(prevsecs != secs) {

          var prevsecs = prevsecs + ""
          var secsplit = secs.split("");
          var prevsecsplit = prevsecs.split("");

          if(prevsecsplit[0] != secsplit[0]) numberflip('#secl',secsplit[0]);
          if(prevsecsplit[1] != secsplit[1]) numberflip('#secr',secsplit[1]);

        };



        function numberflip(which,number){

  		if(number != 0) $(which).animate({marginTop: '-'+parseInt((number*140),10)+'px'}, 250, 'linear');

  		if(number == 0) {

                var getmargin = parseInt(($(which).css('margin-top')), 10);

                $(which).animate({marginTop: parseInt((getmargin-140),10)+'px'}, 250, 'linear', function(){
                  $(this).css("margin-top","0px")
  		  });

              };

        };

        setTimeout(function(){checktime(hour,mins,secs);}, 200);

      };
      
      
      function checktime2(prevhour,prevmins,prevsecs,hour,mins,secs){




//var now = new Date(startDateLong);
//          var hour = now.getHours();
       
    	  if(hour < 10) hour = "0" + hour;

          //var mins = now.getMinutes();
          if(mins < 10) mins = "0" + mins;

    	//var secs = now.getSeconds();
    	if(secs < 10) secs = "0" + secs;  

          var hour = hour + "";
          var mins = mins + "";
    	var secs = secs + "";

          if(prevhour != hour) {

            var prevhour = prevhour + ""
            var hoursplit = hour.split("");
            var prevhoursplit = prevhour.split("");

            if(prevhoursplit[0] != hoursplit[0]) numberflip('#hourl2',hoursplit[0]);
            if(prevhoursplit[1] != hoursplit[1]) numberflip('#hourr2',hoursplit[1]);

          };

          if(prevmins != mins) {

            var prevmins = prevmins + ""
            var minsplit = mins.split("");
            var prevminsplit = prevmins.split("");

            if(prevminsplit[0] != minsplit[0]) numberflip('#minl2',minsplit[0]);
            if(prevminsplit[1] != minsplit[1]) numberflip('#minr2',minsplit[1]);

          };

          if(prevsecs != secs) {

            var prevsecs = prevsecs + ""
            var secsplit = secs.split("");
            var prevsecsplit = prevsecs.split("");

            if(prevsecsplit[0] != secsplit[0]) numberflip('#secl2',secsplit[0]);
            if(prevsecsplit[1] != secsplit[1]) numberflip('#secr2',secsplit[1]);

          };



          function numberflip(which,number){

    		if(number != 0) $(which).animate({marginTop: '-'+parseInt((number*140),10)+'px'}, 250, 'linear');

    		if(number == 0) {

                  var getmargin = parseInt(($(which).css('margin-top')), 10);

                  $(which).animate({marginTop: parseInt((getmargin-140),10)+'px'}, 250, 'linear', function(){
                    $(this).css("margin-top","0px")
    		  });

                };

          };
          //secs=parseInt(secs) +1;
          //startDateLong = parseInt(startDateLong)+1000;
          var tempDate = new Date(1900,1,1,hour,mins,secs,0);
          var tempDateLong = parseInt(tempDate.getTime())+1000;
          var nextDate = new Date(tempDateLong);
          setTimeout(function(){checktime2(hour,mins,secs,nextDate.getHours(),nextDate.getMinutes(),nextDate.getSeconds());}, 1000);

        };

