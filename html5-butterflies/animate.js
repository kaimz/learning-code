$(document).ready(function () {
    var delay = 1;
    var DELAY_STEP = 200;
    var animationOptions = { opacity: 1, top: 0};

    $('h1').animate(animationOptions).promise()
        .pipe(animateMain)
        .pipe(animateLocationIcon);

    function animateMain() {
        var dfd = $.Deferred();
        var els = $('.animate-init');
        var size = els.size();

        els.each(function (index, el) {
            delay++;
            $(el).delay(index * DELAY_STEP)
                .animate(animationOptions);
            (size - 1 === index) && dfd.resolve();
        });
        return dfd.promise();
    }

    function animateLocationIcon() {
        $('.location-icon').delay(delay * DELAY_STEP).animate({
            opacity: 1,
            top: 0
        }).promise().done(animationQuote);
    }

    function animationQuote() {}

    $(document.body).on('keydown', function (event) {
        // Press 'b' key
        if (event.which === 66) {
            $('.relocate-location').text('Bookmark Page');
            $('.relocating').css('opacity', 1);

            window.setTimeout(function () {
                window.location.href = '/bookmarks.html';
            }, 1000);
        }
    });
});