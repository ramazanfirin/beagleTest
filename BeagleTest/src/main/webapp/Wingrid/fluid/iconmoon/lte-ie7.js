/* Use this script if you need to support IE 7 and IE 6. */

window.onload = function() {
	function addIcon(el, entity) {
		var html = el.innerHTML;
		el.innerHTML = '<span style="font-family: \'icomoon\'">' + entity + '</span>' + html;
	}
	var icons = {
			'icon-home' : '&#xe000;',
			'icon-play' : '&#xe001;',
			'icon-phone' : '&#xe002;',
			'icon-reply' : '&#xe003;',
			'icon-search' : '&#xe004;',
			'icon-lock' : '&#xe005;',
			'icon-link' : '&#xe006;',
			'icon-sad' : '&#xe007;',
			'icon-shocked' : '&#xe008;',
			'icon-mail' : '&#xe009;',
			'icon-mail-2' : '&#xe00a;',
			'icon-google' : '&#xe00b;',
			'icon-google-plus' : '&#xe00c;',
			'icon-google-plus-2' : '&#xe00d;',
			'icon-google-plus-3' : '&#xe00e;',
			'icon-google-plus-4' : '&#xe00f;',
			'icon-facebook' : '&#xe010;',
			'icon-facebook-2' : '&#xe011;',
			'icon-facebook-3' : '&#xe012;',
			'icon-twitter' : '&#xe013;',
			'icon-twitter-2' : '&#xe014;',
			'icon-twitter-3' : '&#xe015;',
			'icon-feed' : '&#xe016;',
			'icon-feed-2' : '&#xe017;',
			'icon-feed-3' : '&#xe018;',
			'icon-youtube' : '&#xe019;',
			'icon-youtube-2' : '&#xe01a;',
			'icon-vimeo' : '&#xe01b;',
			'icon-vimeo2' : '&#xe01c;',
			'icon-vimeo-2' : '&#xe01d;',
			'icon-lanyrd' : '&#xe01e;',
			'icon-flickr' : '&#xe01f;',
			'icon-flickr-2' : '&#xe020;',
			'icon-flickr-3' : '&#xe021;',
			'icon-flickr-4' : '&#xe022;',
			'icon-github' : '&#xe023;',
			'icon-wordpress' : '&#xe024;',
			'icon-joomla' : '&#xe025;',
			'icon-windows' : '&#xe026;',
			'icon-android' : '&#xe027;',
			'icon-finder' : '&#xe028;',
			'icon-apple' : '&#xe029;',
			'icon-yahoo' : '&#xe02a;',
			'icon-tumblr' : '&#xe02b;',
			'icon-tumblr-2' : '&#xe02c;',
			'icon-blogger' : '&#xe02d;',
			'icon-blogger-2' : '&#xe02e;',
			'icon-windows8' : '&#xe02f;',
			'icon-linkedin' : '&#xe030;',
			'icon-lastfm' : '&#xe031;',
			'icon-delicious' : '&#xe032;',
			'icon-stumbleupon' : '&#xe033;',
			'icon-paypal' : '&#xe034;',
			'icon-pinterest' : '&#xe035;',
			'icon-pinterest-2' : '&#xe036;',
			'icon-stumbleupon-2' : '&#xe037;',
			'icon-paypal-2' : '&#xe038;',
			'icon-paypal-3' : '&#xe039;',
			'icon-html5' : '&#xe03a;',
			'icon-file-css' : '&#xe03b;',
			'icon-file-xml' : '&#xe03c;',
			'icon-html5-2' : '&#xe03d;',
			'icon-css3' : '&#xe03e;',
			'icon-chrome' : '&#xe03f;',
			'icon-firefox' : '&#xe040;',
			'icon-IE' : '&#xe041;',
			'icon-opera' : '&#xe042;',
			'icon-safari' : '&#xe043;',
			'icon-IcoMoon' : '&#xe044;'
		},
		els = document.getElementsByTagName('*'),
		i, attr, html, c, el;
	for (i = 0; i < els.length; i += 1) {
		el = els[i];
		attr = el.getAttribute('data-icon');
		if (attr) {
			addIcon(el, attr);
		}
		c = el.className;
		c = c.match(/icon-[^\s'"]+/);
		if (c && icons[c[0]]) {
			addIcon(el, icons[c[0]]);
		}
	}
};