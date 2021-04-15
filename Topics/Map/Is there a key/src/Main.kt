fun containsKeyAndValue(map: Map<String, String>, value: String) =
    map[value]?.let { map.containsValue(value) } ?: false
