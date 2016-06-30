<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('#submitBtn').click(function() {
        var cmt = $('#cmt').val();
        $.ajax({
            type : 'POST',
            data : {
                cmt : cmt,
                action : 'EnterMsg'
            },
            url : 'SubmitComment',
            success : function(result) {
                $('#view2').text(result);
            }
        });
    });
});

</script>
</head>
<body>
    <fieldset>
        <legend>Enter Message</legend>
    <form>
        Ques.<input type="text" id="cmt"> <input type="button"
            value="Send" id="submitBtn"><br> <span id="post1"></span>
    </form>
</fieldset>
<fieldset>
    <legend>View Message</legend>
    <form>
        <div id='view2'></div>
        <br>
    </form>
</fieldset>