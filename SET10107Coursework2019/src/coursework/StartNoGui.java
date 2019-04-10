package coursework;

import model.Fitness;
import model.Individual;
import model.LunarParameters.DataSet;
import model.NeuralNetwork;

/**
 * Example of how to to run the {@link ExampleEvolutionaryAlgorithm} without the need for the GUI
 * This allows you to conduct multiple runs programmatically 
 * The code runs faster when not required to update a user interface
 *
 */
public class StartNoGui {

	public static void main(String[] args) {
		/**
		 * Train the Neural Network using our Evolutionary Algorithm 
		 * 
		 */

		int numbofRuns = 10;
		
		double[] trainingScores = new double[10];
		double[] testScores = new double[10];
		NeuralNetwork nn = new ExampleEvolutionaryAlgorithm();		
		for(int i = 0; i<numbofRuns; i++)
		{
			//Parameters.maxEvaluations = 20000; // Used to terminate the EA after this many generations
			//Parameters.popSize = 200; // Population Size
			
			//training
			Parameters.setHidden(5);
			Parameters.setDataSet(DataSet.Training);

			nn.run();
			//System.out.println(nn.best);
			double fitnessTraining = Fitness.evaluate(nn);
			trainingScores[i] = fitnessTraining;
		
			
			//test
			Parameters.setDataSet(DataSet.Test);
			double fitnessTest = Fitness.evaluate(nn);
			//System.out.println("Fitness on " + Parameters.getDataSet() + " " + fitnessTest);
			testScores[i] = fitnessTest;

		}
		
		
		//print function
		for(int i = 0; i<numbofRuns; i++)
		{
			System.out.println("Best scores from training " + " no. " + i + " : " + trainingScores[i]);
			System.out.println("Scores from test "+ " no. " + i + " : " + testScores[i]);
		}
		
		/**
		 * Or We can reload the NN from the file generated during training and test it on a data set 
		 * We can supply a filename or null to open a file dialog 
		 * Note that files must be in the project root and must be named *-n.txt
		 * where "n" is the number of hidden nodes
		 * ie  1518461386696-5.txt was saved at timestamp 1518461386696 and has 5 hidden nodes
		 * Files are saved automatically at the end of training
		 *  
		 *  Uncomment the following code and replace the name of the saved file to test a previously trained network 
		 */
		
//		NeuralNetwork nn2 = NeuralNetwork.loadNeuralNetwork("1234567890123-5.txt");
//		Parameters.setDataSet(DataSet.Random);
//		double fitness2 = Fitness.evaluate(nn2);
//		System.out.println("Fitness on " + Parameters.getDataSet() + " " + fitness2);
		
		
		
	}
}
