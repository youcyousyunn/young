function inptseek() {
	'use strict';
    // search & highlight
    (function($, window, document, undefined) {
        var $container = $('.seeks');
        if (!$container.length) return true;
    
        var $input = $container.find('.inputseek'),
            $notfound = $container.find('.seektxt'),
            $items = $container.find('> ul > li'),
            $item = $(),
            itemsIndexed = [];
            
        $items.each(function() {
            itemsIndexed.push($(this).text().replace(/\s{2,}/g, ' ').toLowerCase());
        });
    
        $input.on('keyup', function(e) {
            if (e.keyCode == 13) // enter
            {
                $input.trigger('blur');
                return true;
            }
            $items.each(function() {
                $item = $(this);
                $item.html($item.html().replace(/<span class="highlight">([^<]+)<\/span>/gi, '$1'));
            });
    
            var searchVal = $.trim($input.val()).toLowerCase();
            if (searchVal.length) {
                for (var i in itemsIndexed) {
                    $item = $items.eq(i);
                    if (itemsIndexed[i].indexOf(searchVal) != -1)
                        $item.removeClass('is-hidden').html($item.html().replace(new RegExp(searchVal + '(?!([^<]+)?>)', 'gi'), '<span class="highlight">$&</span>'));
                    else
                        $item.addClass('is-hidden');
                }
            } else $items.removeClass('is-hidden');
    
            $notfound.toggleClass('is-visible', $items.not('.is-hidden').length == 0);
        });
    })(jQuery, window, document);
    
    // toggling items on title press
    
    ;
    (function($, window, document, undefined) {
        $(document).on('click', '.seeks li', function(e) {
            e.preventDefault();
            $(this).parents('li').toggleClass('is-active');
        });
    })(jQuery, window, document);
    // auto-show item content when show results reduces to single
    
    ;
    (function($, window, document, undefined) {
        var $container = $('.seeks');
        if (!$container.length) return true;
        var $input = $container.find('.inputseek'),
            $items = $container.find('> ul > li'),
            $item = $();
        $input.on('keyup', function() {
            $item = $items.not('.is-hidden');
            if ($item.length == 1)
                $item.addClass('js--autoshown is-active');
            else
                $items.filter('.js--autoshown').removeClass('js--autoshown is-active');
        });
    })(jQuery, window, document);
}
