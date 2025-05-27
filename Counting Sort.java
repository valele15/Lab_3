//Puse esta parte del codigo por separado ya que se terminaba solicitando como un punto aparte, este void fue implmentado en la clase Dataset y de ahi se tomaron
//los calculos para la medicion del tiempo de ejecución de este algoritmo.


public void countingSort(ArrayList<Game> list) {
    int maxQuality = 100; // Rango conocido (0-100)
    
    // 1. Inicializar arreglo de conteo
    int[] count = new int[maxQuality + 1];
    ArrayList<Game> output = new ArrayList<>(list.size());

    // 2. Contar frecuencias de cada calidad
    for (Game game : list) {
        count[game.getQuality()]++;
    }

    // 3. Acumular frecuencias para obtener índices
    for (int i = 1; i <= maxQuality; i++) {
        count[i] += count[i - 1];
    }

    // 4. Construir el arreglo ordenado
    for (int i = list.size() - 1; i >= 0; i--) {
        Game game = list.get(i);
        output.add(count[game.getQuality()] - 1, game);
        count[game.getQuality()]--;
    }

    // Actualizar la lista original
    list.clear();
    list.addAll(output);
}


//Integración en sortByAlgorithm:

case "countingSort":
    if ("quality".equals(attribute)) {
        this.countingSort(sortedData);
    } else {
        throw new IllegalArgumentException("CountingSort solo aplica para 'quality'");
    }
    break;


