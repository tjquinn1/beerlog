<html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Home Page</title>
</head>
<body>
<div id="content" role="main">
    <section class="row colset-2-its">
        <h1>Welcome to our Home Page!</h1>
        <g:link resource="beer" action="create">New Beer</g:link>
        <g:link resource="hop" action="create">New Hop</g:link>
        <g:link resource="grain" action="create">New Grain</g:link>
        <table>
            <g:each in="${beers}" var="beer" >
                <tr>
                    <td>
                        <g:link resource="beer" action="show" id="${beer.id}">${beer.name}</g:link>
                    </td>
                </tr>

            </g:each>
        </table>

    </section>

</div>
</body>
</html>