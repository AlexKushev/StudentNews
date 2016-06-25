$(document).ready(function() {
    var $LoginButton = $("#login-button"),
        $RegisterButton = $("#register-button");
    $LoginButton.on("click", function() {
        callForLogin();
    });

    $('#login-password').keydown(function(e) {
        if (e.keyCode == 13) {
            callForLogin();
        }
    });

    $RegisterButton.click(function() {
        var oRegisterData = {
            user: {
                firstName: $("#register-firstName").val(),
                lastName: $("#register-lastName").val(),
                userName: $("#register-userName").val(),
                password: $("#register-password").val()
            }
        };

        if (!validate()) {
            return;
        }

        $.ajax({
                url: 'rest/user/register',
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(oRegisterData)
            })
            .success(function() {
                alert("Register success! You can now login in the system!");
            })
            .fail(function() {
                alert("Invalid data or user with this data already exists!");
            })
            .always(function() {
                $("#register-form").submit();
            });
    });

    function callForLogin() {

        var oUserData = {
            user: {
                userName: $("#login-userName").val(),
                password: $("#login-password").val()
            }
        };


        $.ajax({
            url: 'rest/user/login',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(oUserData),
            statusCode: {
                401: function() {
                    alert("Wrong username or password!");
                },
                200: function() {

                    $.ajax({
                        type: "GET",
                        complete: function(data) {
                            window.location.replace("home.html");
                        }
                    });
                }
            }
        });
    }

    function DO_NOT_EVER_WRITE_SUCH_CODE(string, position) {
        return string.responseText.split(',')[position].split(':')[1].slice(1, (string.responseText.split(',')[position].split(':')[1].length));
    }

    function validate() {
        var $FirstName = $('#register-firstName'),
            $LastName = $('#register-lastName'),
            $Email = $('#register-email'),
            $ReEmail = $('#register-confirm-email'),
            $Password = $('#register-password'),
            $RePassword = $('#register-password-re');

        if ($Password.val() !== $RePassword.val()) {
            alert('Passwords do not match!');
            return false;
        }

        // function validateEmail(email) {
        //     var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
        //     return re.test(email);
        // }

        // if (($FirstName.val().length === 0) || ($FirstName.val().length > 20) || ($LastName.val().length === 0) || ($LastName.val().length > 20) || (!validateEmail($Email.val())) || $ReEmail.val() !== $Email.val() || $Password.val().length < 8) {
        //     alert('Incorrect data! Check again!');
        //     return false;
        // }
        return true;
    }
});