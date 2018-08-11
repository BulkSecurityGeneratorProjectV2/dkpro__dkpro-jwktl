/*******************************************************************************
 * Copyright 2013
 * Ubiquitous Knowledge Processing (UKP) Lab
 * Technische Universität Darmstadt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package de.tudarmstadt.ukp.jwktl.parser.de.components.nountable;

import de.tudarmstadt.ukp.jwktl.api.entry.WiktionaryWordForm;
import de.tudarmstadt.ukp.jwktl.api.util.GrammaticalCase;
import junit.framework.TestCase;

public class DativeHandlerTest extends TestCase {

	private DativeHandler dativeHandler;

	@Override
	protected void setUp() throws Exception {
		dativeHandler = new DativeHandler();
	}

	public void testCanHandle() {
		assertFalse(dativeHandler.canHandle(null, null, null, null));
		assertFalse(dativeHandler.canHandle("Vitad", null, null, null));
		assertTrue(dativeHandler.canHandle("Dativ Singular", null, null, null));
		assertTrue(dativeHandler.canHandle("Dativ", null, null, null));
		assertFalse(dativeHandler.canHandle(" Dativ Singular", null, null, null));
		assertTrue(dativeHandler.canHandle("Wem? (Einzahl)", null, null, null));
		assertTrue(dativeHandler.canHandle("Wem?(Einzahl)", null, null, null));
		assertFalse(dativeHandler.canHandle(" Wem? (Einzahl)", null, null, null));
	}

	public void testGenitivSingular() {
		WiktionaryWordForm wordForm = new WiktionaryWordForm("test");
		dativeHandler.handle("Dativ Singular", "test", wordForm, null);
		assertEquals(GrammaticalCase.DATIVE, wordForm.getCase());
	}

	public void testWerOderWasEinzahl() {
		WiktionaryWordForm wordForm = new WiktionaryWordForm("test");
		dativeHandler.handle("Wem? (Einzahl)", "test", wordForm, null);
		assertEquals(GrammaticalCase.DATIVE, wordForm.getCase());
	}
}