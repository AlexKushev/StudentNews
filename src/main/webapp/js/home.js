$(document).ready(function() {

    var $techNews = $("#techNews"),
        $universityNews = $("#universityNews"),
        articleData = null;

    $.getJSON("rest/article", function(data) {
        articleData = data.article;

        for (var i = 0; i < articleData.length; i++) {
            var articleItem = articleData[i];
            if (articleItem.articleType == "TechNews") {
                $techNews.append('<article><h2 class="articleHeading">' + articleItem.title + '</h2><p class="author">posted on ' + articleItem.postDate + '</p><p class="articleContent">' + articleItem.text + '</p></article>');
            }
            else {
                $universityNews.append('<article><h2 class="articleHeading">' + articleItem.title + '</h2><p class="author">posted on ' + articleItem.postDate + '</p><p class="articleContent">' + articleItem.text + '</p></article>');
            }
        }
    });

    $("#universityNewsBut").click(function(){
        if($("#universityNews").hasClass('hidden')) {
            $("#universityNews").removeClass('hidden');
            $("#techNews").addClass('hidden');
        }
    });

    $("#techNewsBut").click(function(){
        if($("#techNews").hasClass('hidden')) {
            $("#techNews").removeClass('hidden');
            $("#universityNews").addClass('hidden');
        }
    });

    $('#logout-button').click(function() {
        $.ajax({
            url: 'rest/user/logout',
            type: "GET",
            statusCode: {
                204: function() {
                    alert("Успешно излизане от системата!");
                    window.location.replace("index.html");
                }
            }
        });
    });
});
