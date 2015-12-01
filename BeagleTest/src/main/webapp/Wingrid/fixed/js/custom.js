
$(function(){

			"use strict";
			// jquery.mb.bgndGallery - background slideshow
            $.mbBgndGallery.buildGallery({
                containment:"body",
                timer:2000,
                effTimer:6000,
                controls:"#controls",
                grayScale:false,
                shuffle:true,
                preserveWidth:false,
                //effect:"zoom",
				effect:{enter:{transform:"scale("+(1+ Math.random()*2)+")",opacity:0},exit:{transform:"scale("+(Math.random()*2)+")",opacity:0}},

                // If your server allow directory listing you can use:
                // (however this doesn't work locally on your computer)

                //folderPath:"testImage/",

                // else:

                images:[
                "images/background/1.jpg",
				"images/background/2.jpg",
				"images/background/4.jpg",
				"images/background/5.jpg",
				"images/background/6.jpg"
                ]


            });


			
			
			//staple - Adaptive Thumbnail Pile Effect with Automatic Grouping 
				var $grid = $( '#tp-grid' ),
					$name = $( '#name' ),
					$close = $( '#close' ),
					$loader = $( '<div class="loader"><i></i><i></i><i></i><i></i><i></i><i></i><span>Loading...</span></div>' ).insertBefore( $grid ),
					stapel = $grid.stapel( {
						onLoad : function() {
							$loader.remove();
						},
						onBeforeOpen : function( pileName ) {
							$name.html( pileName );
						},
						onAfterOpen : function( pileName ) {
							$close.show(pileName);
						}
					} );

				$close.on( 'click', function() {
					$close.hide();
					$name.empty();
					stapel.closePile();
				} );			
				
				
				//count down timer
				
				$("#countdown").countdown({
					date: "12 September 2013 11:59:59", // add the countdown's end date (i.e. 3 november 2012 12:00:00)
					format: "on" // on (03:07:52) | off (3:7:52) - two_digits set to ON maintains layout consistency
				},
				
				function() { 
					// the code here will run when the countdown ends
				
				});				
				
				
				// your google map container
				$('.gmap').mobileGmap({
					markers: [
						{
							position: 'center',
							info: '<h2>Wingrid</h2>Mirpur, Dhaka<br />Bangladesh',
							showInfo: true
						}
					]
				});		
				
				
				//Sliding inner pages
				$('#container').fullContent({
					stages: 'div',
					mapPosition: [{v: 3, h: 3}, {v: 3, h: 1}, {v: 1, h: 3}, {v: 6, h: 3}, {v: 6, h: 6}, {v: 3, h: 6}],
					stageStart: 1,
					speedTransition: 800, 
					idComplement: 'page_'
				});		
				
				
});		
		
		