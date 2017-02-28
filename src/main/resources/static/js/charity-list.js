/**
 * Created by RyanHarper on 2/27/17.
 */
(function() {

    var request = $.ajax({
        url: '/'
    });

    request.done(function (charity) { // the http response-> an array of JSON objects -> posts


        var i, html = '', $div = $("<div>");
        for(i = 0; i < charity.length; i++) {
            var showCharityName = $div.text(charity[i].charity_name).html();
            var showCharityCity = $div.text(charity[i].city).html();
            var showCharityState = $div.text(charity[i].state).html();

            html += '<div>' +
                '<h2>' + showCharityName + '</h2>' +
                '<h3>' + showCharityCity + '</h3>' +
                '<h3>' + showCharityState+ '</h3>' +

                // '<img src="/uploads/' + posts[i].image + '" alt="No image"/>' +

                '<a class="btn btn-default" href="/?/' + charity[i].id + '">Mission</a>' +
                '<h4 style="text-align: left">C: <a href="/?/' + posts[i].user.id + '">'
                + posts[i].user.username + '</a></h4>' +
                '</div>';
        }

        html += '';
        $('#load-charity').html(html);

    });

    request.fail(function (e) {
        alert(e);
    })

})();
