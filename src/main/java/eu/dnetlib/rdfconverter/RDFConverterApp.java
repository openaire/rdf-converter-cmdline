package eu.dnetlib.rdfconverter;

import java.io.File;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RDFConverterApp implements CommandLineRunner {

	private static Logger log = LoggerFactory.getLogger(RDFConverterApp.class);

	private static final String APPLICATION_JAR = "./rdf-converter-cmdline.jar";
	private static final String APPLICATION_TITLE = "Command line RDF converter";

	@Autowired
	private RDFConverterUtils RDFConverterUtils;

	private final static CommandLineParser cmdLineParser = new DefaultParser();

	private final static Options options = new Options()
		.addOption(Option.builder("i")
				.required(true)
				.hasArg(true)
				.longOpt("input")
				.desc("the input directory (REQUIRED)")
				.build())
		.addOption(Option.builder("o")
			.required(true)
			.hasArg(true)
			.longOpt("output")
			.desc("the output directory (REQUIRED)")
			.build())
		.addOption(Option.builder("j")
			.required(false)
			.hasArg(false)
			.longOpt("jsonld")
			.desc("print input data with json-ld format")
			.build())
		.addOption(Option.builder("h")
			.longOpt("help")
			.required(false)
			.hasArg(false)
			.desc("help")
			.build())
		.addOption(Option.builder("v")
			.required(false)
			.hasArg(false)
			.desc("verbose")
			.build())
		.addOption(Option.builder("vv")
			.required(false)
			.hasArg(false)
			.desc("show debug logs")
			.build());

	public static void main(final String[] args) {

		// TO AVOID EXCEPTIONS WITH MANDATORY FIELDS
		for (final String s : args) {
			if (s.equals("-h") || s.equals("--help")) {
				printHelpAndExit(options);
			}
		}

		try {

			final CommandLine cmd = cmdLineParser.parse(options, args, false);

			if (cmd.hasOption("v")) {
				SpringApplication.run(RDFConverterApp.class, ArrayUtils.add(args, "--logging.level.root=INFO"));
			} else if (cmd.hasOption("vv")) {
				SpringApplication.run(RDFConverterApp.class, ArrayUtils.add(args, "--logging.level.root=DEBUG"));
			} else {
				SpringApplication.run(RDFConverterApp.class, args);
			}
		} catch (final ParseException e) {
			System.err.println("\nERROR: " + e.getMessage());
			printHelpAndExit(options);
		}
	}

	@Override
	public void run(final String... args) throws Exception {
		System.out.println();

		log.info(String.format("**** EXECUTING - %s ***", APPLICATION_TITLE));

		final CommandLine cmd = cmdLineParser.parse(options, args, true);
		final File inputDir = new File(cmd.getOptionValue("i"));
		final File outputDir = prepareDir(cmd.getOptionValue("o"));
		final boolean jsonLDDump = cmd.hasOption("j");

		log.info("* PARAMS: INPUT DIR: " + inputDir);
		log.info("* PARAMS: OUTPUT DIR: " + outputDir);
		log.info("* PARAMS: JSON-LD DUMP: " + jsonLDDump);

		RDFConverterUtils.nQuads2DataciteJson(inputDir, outputDir, jsonLDDump);

		log.info("**** DONE ***");
		System.out.println();

	}

	private File prepareDir(final String path) {
		final File dir = new File(path);

		if (dir.exists() && dir.isDirectory()) {
			log.info("Reusing existent directory: " + path);
			return dir;

		}

		if (!dir.exists() && dir.mkdirs()) {
			log.info("New directory created: " + path);
			return dir;
		}

		log.error("Invalid directory: " + path);
		throw new RuntimeException("Invalid directory: " + path);
	}

	private static void printHelpAndExit(final Options options) {
		final String ln = StringUtils.repeat("=", APPLICATION_TITLE.length());
		System.out.println(String.format("\n%s\n%s\n%s\n", ln, APPLICATION_TITLE, ln));
		new HelpFormatter().printHelp(APPLICATION_JAR, options, true);

		System.exit(1);
	}
}
