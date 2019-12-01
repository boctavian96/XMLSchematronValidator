<schema xmlns="http://purl.oclc.org/dsdl/schematron">
	<pattern>
		<rule context="book">
			<!-- Assert cand test ii fals atunci da mesaj -->
			<assert test="@price > 10">The book price is too small.</assert>

			<!-- Report cand test da true atunci da un mesaj -->
			<report test="@price > 1000">The book is to expensive.</report>
		</rule>
	</pattern>

</schema>